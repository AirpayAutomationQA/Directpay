package com.Airpay.BusinessLogic;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Airpay.PageObject.Airpay_PaymentPage_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class AirPay_Payment_Mode_Debit_Card_BusinessLogic extends Airpay_PaymentPage_PageObject {
	
	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	//Log log = new Log();	
	public AirPay_Payment_Mode_Debit_Card_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("AirPay_Payment_Mode_Debit_Card_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}
	
	public void Debit_cardholderNameCopyPaste() throws Throwable{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Debit card Number input field");
			WebElement HolderName=driver.findElement(By.xpath(DebitCardHolderName));
			HolderName.click();			
			Robot robot = new Robot();
			String str = "par()>>";
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			StringSelection strSel = new StringSelection(str);
			clipboard.setContents(strSel, null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			//Assert.inputTextPaste(driver, DebitCardHolderName, "Debit card Holder Name Field");		   
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	public void Debit_cardProvidingValues() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Debit card Number input field");
			//Assert.inputText(driver, DebitCardNoInput, "9809808!13", "Debit card Number input field");

			Assert.inputText(driver, DebitCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Debit card Holder Name Field");		   
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			System.out.println("test");
			if(Assert.isElementDisplay(driver, DebitCardMakePaymtBtn)){
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");	
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void Debit_cardProvidingValuesTest() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Debit card Number input field");
			//Assert.inputText(driver, DebitCardNoInput, "9809808!13", "Debit card Number input field");

			Assert.inputText(driver, DebitCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Debit card Holder Name Field");		   
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	public void Debit_cardProvidingValuesNotIndiaCard() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Debit card Number input field");
			//Assert.inputText(driver, DebitCardNoInput, "9809808!13", "Debit card Number input field");

			Assert.inputText(driver, DebitCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Debit card Holder Name Field");		   
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public void Debit_cardProvidingWithoutName() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Debit card Number input field");
			//Assert.inputText(driver, DebitCardNoInput, "9809808!13", "Debit card Number input field");

			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");		   

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void Debit_cardProvidingValues_withHolderName() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Debit card Number input field");
			Assert.inputText(driver, DebitCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Debit card Holder Name Field");		   
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed", driver);
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void Debit_cardProvidingValuesWithValidData() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Debit card Number input field");
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed", driver);
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	public static String confFees = null;
	public static String PassedAmt = null;
	public static String TotAmt =null;
	public void Debit_SurchargeValuesWithValidCardNumber() throws Exception{
		try{		   		   
			Assert.inputText(driver, DebitCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Debit card Number input field");
			Assert.inputText(driver, DebitCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Debit card Number Exp Date");
			Assert.inputText(driver, DebitCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Debit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed s", driver);
			Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn iplus']", "Amount Plus button");			
			PassedAmt = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
			confFees = driver.findElement(By.xpath("(//*[@class='surcharge_amount'])[1]")).getText().trim();
			TotAmt = driver.findElement(By.xpath("//span[@class='amount-value-block']")).getText().trim();
			Extent_Reporting.Log_report_img("Surcharge filed snap", "Passed", driver);
			Extent_Reporting.Log_Pass("Surcharge Amount: "+confFees, "Total Amount: "+TotAmt);
			Assert.Clickbtn(driver, DebitCardMakePaymtBtn, "Debit Card make payment button");	
			Thread.sleep(10000);
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}
	
	public void SurchargeForCommonFunction() throws Exception{
		try{
			boolean value=driver.findElement(By.className("amt-togglebtn")).isDisplayed();
			if(value=true)
			{
				if(Assert.isElementDisplay(driver, "//div[@class='sumbtn desksumbtn iplus']"))
				{
					Extent_Reporting.Log_report_img("All details has been Entered", "Passed", driver);
					Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn iplus']", "Amount Plus button");			
					PassedAmt = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
					confFees = driver.findElement(By.xpath("(//*[@class='surcharge_amount'])[1]")).getText().trim();
					TotAmt = driver.findElement(By.xpath("//span[@class='amount-value-block']")).getText().trim();
					Extent_Reporting.Log_report_img("Surcharge filed snap", "Passed", driver);
					Extent_Reporting.Log_Pass("Surcharge Amount: "+confFees, "Total Amount: "+TotAmt);
				}
			}
			else
			{
				Extent_Reporting.Log_Pass("Surcharge is not applicable for merchant.", "No surcharge is applicable");
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Surcharge is not applicable for merchant.", "No surcharge is applicable. Kindly verify surcharge flag and surcharge rules in M.A panel.", driver); 
			Log.error("Surcharge Amount field does not exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}
	
	public void SurchargeForCommonFunctionNotclickplus() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, "//div[@class='sumbtn desksumbtn iplus']"))
			{
				Extent_Reporting.Log_report_img("All details has been Entered", "Passed s", driver);
				Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn iplus']", "Amount Plus button");			
				PassedAmt = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
				confFees = driver.findElement(By.xpath("(//*[@class='surcharge_amount'])[1]")).getText().trim();
				TotAmt = driver.findElement(By.xpath("//span[@class='amount-value-block']")).getText().trim();
				Extent_Reporting.Log_report_img("Surcharge filed snap", "Passed s", driver);
				Extent_Reporting.Log_Pass("Surcharge Amount: "+confFees, "Total Amount: "+TotAmt);
				Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn imins']", "Amount minus button");			
			}
			else
			{
				Extent_Reporting.Log_Pass("Surcharge Amount is not applicable: ", "Total Amount: ");
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Surcharge Amount field does not exist", "Failed", driver);   
			Log.error("Surcharge Amount field does not exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}
	
	
	

}
