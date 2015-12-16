package com.primerevenue.osci.pageobjects.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.Iterables;
import com.primerevenue.osci.driver.Browser;
import com.primerevenue.osci.utils.SeleniumUtils;
import com.primerevenue.osci.utils.Synchronizer;

public class POUploadPerformTrade  {
	final static Logger logger = Logger.getLogger(POUploadPerformTrade.class);

	@FindBy(id = "_atfSubmit")
	public WebElement createSellOfferBtn;

	// RKTbuy2
	@FindBy(xpath = "//a[contains(text(),'rktcommunity_BP')]//..//..//td[12]//input[1]")
	public WebElement chkBox;

	@FindBy(id = "_initiateFundingSubmit")
	public WebElement _initiateFundingSubmit;
	
	//Com Module
	@FindBy(id = "bpId")
	public WebElement bPdropdownBox;
	
	@FindBy(id = "_searchSubmit")
	public WebElement _searchSubmit;
	
	@FindBy(xpath = "html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/form/table[2]/tbody/tr/td[8]/input")
	public WebElement distAttemptschkBox;
	
	@FindBy(id = "_specificFiId")
	public WebElement assignFI;
	
	@FindBy(xpath = "//a[contains(text(),'Distribute')]")
	public WebElement distBtn;
	
	//FI Module
	@FindBy(xpath = "html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/form/table[4]/tbody/tr/td[1]/input")
	public WebElement buyOffchkBox;
	
	@FindBy(xpath = "//a[contains(text(),'Accept')]")
	public WebElement accept;
	
	@FindBy(xpath = "html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[1]/a")
	public List<WebElement> eFTStateLinks;
	
	
	public void copyFiles(String source, String destination ) throws IOException {

		//String source = "C:/Dir1";
		File srcDir = new File(source);

		//String destination = "C:/Dir1";
		File destDir = new File(destination);

		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void bounceWFCServices() throws Exception {
		
		// run batch file here..
		Runtime.getRuntime().exec("C:/restartServicesMaj.bat");
		
	}
	
	
	

	public void poTradeFrmSuppplier() {
		PageFactory.initElements(Browser.eDriver, this);

		SCiSupplierSuppMenu objRef = PageFactory.initElements(Browser.eDriver,
				SCiSupplierSuppMenu.class);
		objRef.menuToAvailableToFund();

		SeleniumUtils.clickCheckBox(chkBox, "ON");
		SeleniumUtils.click(createSellOfferBtn);

		SeleniumUtils.click(_initiateFundingSubmit);

	}
	public void poTradeFrmCM() {
		PageFactory.initElements(Browser.eDriver, this);
		
		SCiSupplierCOMMenu objRef = PageFactory.initElements(Browser.eDriver,
				SCiSupplierCOMMenu.class);
		objRef.menuToBOManualDist();
		
		SeleniumUtils.selectOption(bPdropdownBox, "rktcommunity_BP");
		SeleniumUtils.click(_searchSubmit);
		
		SeleniumUtils.clickCheckBox(distAttemptschkBox, "ON");
		SeleniumUtils.selectOption(assignFI, "Rktfi");
		SeleniumUtils.click(distBtn);
			
	}
	public void poTradeFrmFI() {
		PageFactory.initElements(Browser.eDriver, this);
		
		SCiSupplierFIMenu objRef = PageFactory.initElements(Browser.eDriver,
				SCiSupplierFIMenu.class);
		Synchronizer.explicitWait(300);
		objRef.menuToFIBuyOffer();
		SeleniumUtils.clickCheckBox(buyOffchkBox, "ON");
		SeleniumUtils.click(accept);
		
		int eftStatesCount = eFTStateLinks.size();
		System.out.println(eftStatesCount);
		WebElement latestStatement = Iterables.getLast(eFTStateLinks);
		String sssaaaa = latestStatement.getText();
		System.out.println(sssaaaa);
		SeleniumUtils.click(latestStatement);
		
	}
}
