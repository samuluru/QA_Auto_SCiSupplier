package com.primerevenue.osci.pageobjects.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.primerevenue.osci.driver.Browser;
import com.primerevenue.osci.utils.SeleniumUtils;
import com.primerevenue.osci.utils.Synchronizer;
import com.google.common.collect.Iterables;

public class SPMaintainMembership {

	final static Logger logger = Logger.getLogger(SPMaintainMembership.class);

	@FindBy(id = "communityBuyers-tab")
	public WebElement commBuyersTab;

	/* Buyer Name = rktbuyer2 */
	@FindBy(xpath = "//table[@id='row']/tbody/tr[2]/td[5]/a")
	public WebElement view;

	@FindBy(xpath = "html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr/td[2]/a")
	public List<WebElement> buyerNameLink;

	@FindBy(xpath = "//a[contains(@href, 'javascript: addBuyerProgram();')]")
	public WebElement addBtn;

	/* Buyer Name = rktbuyer2 */
	@FindBy(xpath = "//a[contains(text(),'rktcommunity_BP')]")
	public WebElement rktcommunity_BP;

	public void maintainMembTab() {

		PageFactory.initElements(Browser.eDriver, this);

		SeleniumUtils.click(commBuyersTab);
		SeleniumUtils.click(view);
		SeleniumUtils.click(addBtn);

	}

	public void maintainMembTabNavAddSupplier() {

		PageFactory.initElements(Browser.eDriver, this);
		
		SeleniumUtils.click(commBuyersTab);
		SeleniumUtils.click(view);
		SeleniumUtils.click(view);
		SeleniumUtils.click(rktcommunity_BP);
		
	}

	public void testingTableContents() {

		PageFactory.initElements(Browser.eDriver, this);

		SeleniumUtils.click(commBuyersTab);
	
		//Buyer Name or First name
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[1]/td[2]
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[2]/td[2]
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[3]/td[2]	
		
		String xpath_Start = ("html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[");
		String xpath_End = ("]/td[2]/a");
		int i=2;
		
		while(SeleniumUtils.isElementPresent(xpath_Start+i+xpath_End)) {
		String buyerName = Browser.eDriver.findElement(By.xpath(xpath_Start+i+xpath_End)).getText();
		if(buyerName.equalsIgnoreCase("rktbuyer2")) {
			System.out.println("Buyer Name Found : : : : :" + buyerName);
			
			//Browser.eDriver.findElement(By.xpath(xpath_Start+i+xpath_End)).click();
		
		// Check for the Buyer Programs 
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[1]/td[5]
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[2]/td[5]
		//html/body/div[1]/div/div/div/div/table[4]/tbody/tr/td[2]/table/tbody/tr/td[2]/div/div/div[3]/form/table[1]/tbody/tr[3]/td[5]
		
		String buyerProgramView = Browser.eDriver.findElement(By.xpath(xpath_Start+i+xpath_End.replace("td[2]","td[5]"))).getText();
		
		if (buyerProgramView.equalsIgnoreCase("View")) {
			System.out.println("Buyer Program Found : : : : :" + buyerProgramView);
			Browser.eDriver.findElement(By.xpath(xpath_Start+i+xpath_End.replace("td[2]","td[5]"))).click();
			break;
		}
		
		}
		i++; // Test under process
	
		}
	}
}


	


