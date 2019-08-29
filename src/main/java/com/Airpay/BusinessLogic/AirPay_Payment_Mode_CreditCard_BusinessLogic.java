package com.Airpay.BusinessLogic;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.Airpay.PageObject.Airpay_PaymentPage_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class AirPay_Payment_Mode_CreditCard_BusinessLogic extends Airpay_PaymentPage_PageObject {

	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	//Log log = new Log();	
	public AirPay_Payment_Mode_CreditCard_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("AirPay_Payment_Mode_CreditCard_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}

	public void LocalHost_Page_Validation() throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerFirstName))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");
				Assert.inputText(driver, BuyerFirstName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName"), "Buyer First Name");
				//Assert.inputText(driver, BuyerLastName, Excel_Handling.Get_Data(TC_ID, "BuyerLastName"), "Buyer Last Name");
				//Assert.inputText(driver, BuyerPinCode, Excel_Handling.Get_Data(TC_ID, "Pin_Code"), "Buyer Pin Code");    		
				//String  string = RandomStringUtils.randomAlphabetic(8);		
				//System.out.println("Random 1 = " + string);				
				//Assert.inputText(driver, Order_Id, string, "Order_Id");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");            
				String errVerfiy = driver.findElement(By.xpath("//span[@class='alert alert-error']")).getText();
				if(errVerfiy.contains("Either email or contact number is mandatory") || errVerfiy.contains("Invalid Order Id") ||errVerfiy.contains("Invalid Contact No") ||errVerfiy.contains("Wrong Checksum")
						||errVerfiy.contains("Invalid Email Id") ||errVerfiy.contains("Invalid Amount")|| errVerfiy.contains("Transaction Update Failed - Merchant Transaction Id not valid")
						|| errVerfiy.contains("Invalid First Name")||errVerfiy.contains("Invalid Last Name")||errVerfiy.contains("Invalid Pincode")
						||errVerfiy.contains("Oops! An error occurred while completing your request. Our engineers have been notified and are working towards resolving it as soon as possible."))
				{  
					Extent_Reporting.Log_report_img("Respective error is exist ", "Passed", driver);
					Extent_Reporting.Log_Pass("Respective error is exist as :"+errVerfiy, "Passed");           	
				}else{ 
					Extent_Reporting.Log_Fail("Respective error does not exist :"+errVerfiy, "Might be provided valid data", driver);
					throw new Exception("error verification");
				}
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Payment page is exist", "Might be provided valid data", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * Following method is used for Filling up the local host details page.
	 * @throws Exception
	 */
	public void LocalHost_Page_ValidationWithoutOrderGenerate() throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerFirstName))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");
				Assert.inputText(driver, BuyerFirstName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName"), "Buyer First Name");
				//Assert.inputText(driver, BuyerLastName, Excel_Handling.Get_Data(TC_ID, "BuyerLastName"), "Buyer Last Name");
				//Assert.inputText(driver, BuyerPinCode, Excel_Handling.Get_Data(TC_ID, "Pin_Code"), "Buyer Pin Code");    		
				/*String  string = RandomStringUtils.randomAlphabetic(8);		
				System.out.println("Random 1 = " + string);				
				Assert.inputText(driver, Order_Id, string, "Order_Id");*/
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");    
				Thread.sleep(1500);
//				/*
//				 * String errVerfiy =
//				 * driver.findElement(By.xpath("//span[@class='alert alert-error']")).getText();
//				 * if(errVerfiy.contains("Either email or contact number is mandatory") ||
//				 * errVerfiy.contains("Invalid Order Id")
//				 * ||errVerfiy.contains("Invalid Contact No")
//				 * ||errVerfiy.contains("Wrong Checksum")
//				 * ||errVerfiy.contains("Invalid Email Id")
//				 * ||errVerfiy.contains("Invalid Amount")|| errVerfiy.
//				 * contains("Transaction Update Failed - Merchant Transaction Id not valid") ||
//				 * errVerfiy.contains("Invalid First Name")||errVerfiy.
//				 * contains("Invalid Last Name")||errVerfiy.contains("Invalid Pincode")
//				 * ||errVerfiy.
//				 * contains("Oops! An error occurred while completing your request. Our engineers have been notified and are working towards resolving it as soon as possible."
//				 * )) { Extent_Reporting.Log_report_img("Respective error is exist ", "Passed",
//				 * driver);
//				 * Extent_Reporting.Log_Pass("Respective error is exist as :"+errVerfiy,
//				 * "Passed"); }else{
//				 * Extent_Reporting.Log_Fail("Respective error does not exist :"+errVerfiy,
//				 * "Might be provided valid data", driver); throw new
//				 * Exception("error verification"); }
//				 */
				String value=driver.findElement(By.xpath(firstnameerror)).getText();
				
				/*
				 * if(driver.findElement(By.xpath(firstnameerror)).getText().
				 * equalsIgnoreCase("Please enter full name")||
				 * driver.findElement(By.xpath(Mailiderror)).getText().
				 * equalsIgnoreCase("Please enter a email id")
				 * ||driver.findElement(By.xpath(PhoneNumbererror)).getText().
				 * equalsIgnoreCase("Please enter valid mobile number")
				 * ||driver.findElement(By.xpath(Amounterror)).getText().
				 * equalsIgnoreCase("Please enter valid amount") ) {
				 * Extent_Reporting.Log_report_img("Respective error is exist ", "Passed",
				 * driver); } else { Extent_Reporting.Log_Fail("Respective error doesnt exist"
				 * ,"Might have provided correct data", driver); }
				 */
				if(driver.findElement(By.xpath(firstnameerror)).getText().equalsIgnoreCase("Please enter full name"))
				{
					Extent_Reporting.Log_report_img("Respective error is exist:- "+driver.findElement(By.xpath(firstnameerror)).getText(), "Passed",driver);
					Extent_Reporting.Log_Pass("First name validation ","Failed to vaildate as incorrect data pass"); 
				}
				else
					if(driver.findElement(By.xpath(Mailiderror)).getText().equalsIgnoreCase("Please enter a email id"))
					{
						Extent_Reporting.Log_report_img("Respective error is exist:- "+driver.findElement(By.xpath(Mailiderror)).getText(), "Passed",driver);
						Extent_Reporting.Log_Pass("Email id validation ","Failed to vaildate as incorrect data pass"); 
					}
					else
						if(driver.findElement(By.xpath(PhoneNumbererror)).getText().equalsIgnoreCase("Please enter valid mobile number"))
						{
							Extent_Reporting.Log_report_img("Respective error is exist:- "+driver.findElement(By.xpath(PhoneNumbererror)).getText(), "Passed",driver);
							Extent_Reporting.Log_Pass("Phone number validation ","Failed to vaildate as incorrect data pass"); 
						}
						else
							if(driver.findElement(By.xpath(Amounterror)).getText().equalsIgnoreCase("Please enter valid amount"))
							{
								Extent_Reporting.Log_report_img("Respective error is exist:- "+driver.findElement(By.xpath(Amounterror)).getText(), "Passed",driver);
								Extent_Reporting.Log_Pass("Amount validation ","Failed to vaildate as incorrect data pass"); 
							}
							else 
							{
								Extent_Reporting.Log_Fail("Respective error doesnt exist","Might have provided correct data", driver); 
							}
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Payment page is exist", "Might be provided valid data", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}



	public void Verify_Credit_Card_Fields() throws Exception{
		try{
			Assert.isElementDisplayed(driver, CreditCardNoInput, "Credit card Number input field");
			Assert.isElementDisplayed(driver, CreditCardHolderName, "Credit card Holder Name Field");		   
			Assert.isElementDisplayed(driver, CreditCardExpDate, "Credit card Number Exp Date");
			Assert.isElementDisplayed(driver, CreditCardCVVCode, "Credit card Number CVVCode");
			Assert.isElementDisplayed(driver, CreditCardMakePaymtBtn, "Credit card Number MakePayment ");

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}


	public void Credit_Card_Field_ErrVerify() throws Exception{
		try{
			
				if(driver.findElement(By.xpath("//div[contains(@class,'errorvalue focused')]")).isDisplayed())
			{
					String error=driver.findElement(By.xpath("//div[contains(@class,'errorvalue focused')]//following-sibling::div[@class='form-group']/span[@class='help']")).getText();
					System.out.println(error);
					Extent_Reporting.Log_Pass("Error red line occurs: "+error, "Passed");
			}
				else
					if(Assert.isElementDisplayed(driver, CreditCardMakePaymtBtn, "Credit Market Payment button"))
			{			   
				Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
				List<WebElement> ErrCreditField = driver.findElements(By.xpath(CreditCardErrField));
				System.out.println("Blank Field is having: "+ErrCreditField.size() );
				if(ErrCreditField.size()<=4)
				{
					for(int i=0;i<ErrCreditField.size();i++ )
					{
						String ErrFields = ErrCreditField.get(i).getText();
						System.out.println(ErrFields);
						Extent_Reporting.Log_Pass("Error red line : "+ErrFields, "Passed");
						Extent_Reporting.Log_report_img("Error exist on credit card field.", "Passed", driver);			
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Market payment button", "Failed", driver);
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Credit card error red line does not exist", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}



	public void Credit_cardProvidingValues() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Credit card Number input field");
			Assert.inputText(driver, CreditCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit card Holder Name Field");		   
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("Credit Card details entered", "Passed", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}


	public void Credit_cardProvidingwithoutHolderName() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Credit card Number input field");
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("Credit Card details entered", "Passed", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}


	public void Credit_cardProvidingValuesWithValidCardNumber() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Credit card Number input field");
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed s", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");	
			Thread.sleep(20000);
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}

	public static String confFees = null;
	public static String PassedAmt = null;
	public static String TotAmt =null;
	public void Credit_cardProvidingSurchargeValuesWithValidCardNumber() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Credit card Number input field");
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed s", driver);
			Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn iplus']", "Amount Plus button");			
			PassedAmt = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
			confFees = driver.findElement(By.xpath("(//*[@class='surcharge_amount'])[1]")).getText().trim();
			TotAmt = driver.findElement(By.xpath("//span[@class='amount-value-block']")).getText().trim();
			Extent_Reporting.Log_report_img("Surcharge filed snap", "Passed", driver);
			Extent_Reporting.Log_Pass("Surcharge Amount: "+confFees, "Total Amount: "+TotAmt);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");	
			Thread.sleep(10000);
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}
	
	
	public void AmountBlockFetchData() throws Exception{
		try{
			PassedAmt = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
			confFees = driver.findElement(By.xpath("(//*[@class='surcharge_amount'])[1]")).getText().trim();
			TotAmt = driver.findElement(By.xpath("//span[@class='amount-value-block']")).getText().trim();
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}
	
	public void Credit_cardholderNameCopyPaste() throws Throwable{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Credit card Number input field");						  

			WebElement HolderName=driver.findElement(By.xpath(CreditCardHolderName));
			HolderName.click();
			Robot robot = new Robot();
			String str = "_par>>";
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			StringSelection strSel = new StringSelection(str);
			clipboard.setContents(strSel, null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);			  
			/* Actions a = new Actions(driver);
			  a.sendKeys(_1232, Keys.chord(Keys.CONTROL,"_123")).perform();
			  a.sendKeys(_1232, Keys.chord(Keys.CONTROL,"v")).perform();*/
			//  Assert.inputTextPaste(driver, CreditCardHolderName, "Credit car Holder Name Field");		   
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit   card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}


	public void EMI_CardHolderName() throws Exception{
		try{		   		   
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, EmiBankNameSelectDropDown, Excel_Handling.Get_Data(TC_ID, "BankName").trim(), "Bank Selected for EMI");			
			driver.findElement(By.xpath(EMICardNumber)).sendKeys("5242160000575125");
			Assert.inputText(driver, EMICardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "EMI Card Number Exp Date");
			Assert.inputText(driver, EMICardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), " card Number CVVCode");
			Assert.Clickbtn(driver, EMICardMakePaymtBtn, "EMI Card make payment button");		   
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Credit_cardProvidingValues_withHolderName() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Credit card Number input field");
			//Assert.inputText(driver, CreditCardHolderName,"param sakole", "Credit card Holder Name Field");		   
			Assert.inputText(driver, CreditCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit card Holder Name Field");		   
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("All details has been Entered", "Passed", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
		}
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Cancel_TransactionPayment() throws Throwable{
		try{	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(20000);
			if(Assert.isElementDisplay(driver, CancelCreditPayment))
			{				   
				   List<WebElement> cancel = driver.findElements(By.xpath(CancelCreditPayment));
				   for(int i=1;i<=cancel.size();i++)
				   {
					   WebElement cancelDisplay = driver.findElement(By.xpath(CancelCreditPayment+"["+i+"]"));
					   if(cancelDisplay.isDisplayed()==true)
					   {
							Assert.Javascriptexecutor_forClick(driver, CancelCreditPayment+"["+i+"]", "Cancel btton");
							Thread.sleep(2000);
							Assert.acceptAlert(driver);
							break;
					   }
				   }			   
					Thread.sleep(20000);
				/*Extent_Reporting.Log_report_img("Respective Bank transaction Page is exist", "Passed", driver);
				Assert.acceptAlert(driver);*/
			}
			else
			{
				Extent_Reporting.Log_Fail("Please choose another payment method", "Failed", driver);
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void Cancel_TransactionPayment_CreditCard() throws Exception{
		try{	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplay(driver, CancelCreditPayment))
			{				
					Thread.sleep(20000);
					System.out.println(driver.getTitle());
					Extent_Reporting.Log_report_img("Respective Bank transaction Page is exist", "Passed", driver);
					Assert.Clickbtn(driver, CancelCreditPayment, "Cancel btton");
					Assert.acceptAlert(driver);
					driver.getCurrentUrl();
					Extent_Reporting.Log_report_img("Receipt Generated", "Passed", driver);		
			}else{

				Extent_Reporting.Log_Fail("Please choose another payment method", "Failed", driver);

			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}		
	public void Cancel_TransactionPayment_DebitCard() throws Exception{
		try{	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplay(driver, CanceldebitPayment))
			{				
					Thread.sleep(15000);
					System.out.println(driver.getTitle());
					Extent_Reporting.Log_report_img("Respective Bank transaction Page is exist", "Passed", driver);
					Thread.sleep(2000);
					System.out.println("Test");
					Assert.Clickbtn(driver, CanceldebitPayment, "Cancel btton");
					Assert.acceptAlert(driver);
					Thread.sleep(2000);
					driver.getCurrentUrl();
					Extent_Reporting.Log_report_img("Receipt Generated", "Passed", driver);		
			}else{

				Extent_Reporting.Log_Fail("Please choose another payment method", "Failed", driver);

			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}		

	public void sessionTimeOut() throws Exception{
		try{
			System.out.println("1");
			if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php")
					&& driver.findElement(By.className("errorwrap")).isDisplayed())
			{
				Extent_Reporting.Log_Fail("Redirected to invalid url", "verify the same manually", driver);
				Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
			}
			else
			{
				for(int i=1;i<=10;i++)
				{
					System.out.println(errMsg);
					if(errMsg.isEmpty()==true){	
					try{
						errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText();
						Thread.sleep(2000);
					}catch(Exception e){						
						System.out.println("second"+errMsg);
						Thread.sleep(1000);
					}
				}else{	  
					if(errMsg.contains("REJECTED. Please try paying using another method?")
							||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
							||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
							||errMsg.contains("FAILED. Please try paying using another method?")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method")
							|| errMsg.contains("VPA is not registered"))
					{
						Extent_Reporting.Log_Pass("Repective Error Message is exist", "Error Msg is:"+errMsg);
						Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);					
						break;
					}
				}
				}
			}
		}catch(Exception e)
		{
			Extent_Reporting.Log_Fail("Repective Error Message does not exist", "It has been reidrected to invalid url, Kindly verify manually", driver);
		}
	}
	
	public void sessionInvalidUserID() throws Exception{
		try{		
				Thread.sleep(15000);
				if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php")&& driver.findElement(By.xpath("//div[@class='errorwrap']")).isDisplayed())
				{
					Extent_Reporting.Log_Pass("Redirected to invalid url", "Error Msg/ URL is: "+driver.getCurrentUrl());
					Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);			
				}
				else if(driver.findElement(By.xpath(CardInvalidErrMsgVerify)).isDisplayed())
				{
					errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText();
					System.out.println(errMsg);
				if(errMsg.contains("REJECTED. Please try paying using another method?")
						||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
						||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
						||errMsg.contains("FAILED. Please try paying using another method?")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method")
						|| errMsg.contains("VPA is not registered")||driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php"))
				{
					Extent_Reporting.Log_Pass("Repective Error Message is exist or redirected to invalid url", "Error Msg/ URL is: "+errMsg+driver.getCurrentUrl());
					Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);						
				}
			else
			{
					Extent_Reporting.Log_Fail("Repsective error message does not exist", "Failed", driver);
			}
		}
		}catch(Exception e)
		{
			Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);
		}
	}
	

	public void sessionTimeOut_errMsg() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php")
					&& driver.findElement(By.className("errorwrap")).isDisplayed())
			{
				Extent_Reporting.Log_Fail("Redirected to invalid url", "verify the same manually", driver);
				Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
			}
			else
			{
				for(int i=1;i<=1;i++)
				{	
				Thread.sleep(12000);
				if(Assert.isElementDisplay(driver, SessionTimer))
				{
					boolean timerState = driver.findElement(By.xpath(SessionTimer)).isDisplayed();			
					System.out.println(timerState);	
					if(timerState==true)
					{					
						Thread.sleep(280000);
						Assert.waitForPageToLoad(driver);
						Thread.sleep(2800);
						//driver.navigate().refresh();
						if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php")
							&& driver.findElement(By.xpath("/html/body/div/table/tbody/tr[6]/td[3]/b")).getText().equalsIgnoreCase("Fail"))
						{
							String errMsg= driver.findElement(By.xpath("/html/body/div/table/tbody/tr[6]/td[3]/b")).getText();
							Extent_Reporting.Log_Pass("Redirected to URL with transaction status", " : "+errMsg);
							Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
							break;
						}
					else
						if(driver.findElement(By.xpath(CardInvalidErrMsgVerify)).isDisplayed())
						{
							errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText();
							if(errMsg.contains("REJECTED. Please try paying using another method?")
							||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
							||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
							||errMsg.contains("FAILED. Please try paying using another method?")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method"))
							{
								Extent_Reporting.Log_Pass("Repective Error Message is exist", "Error Msg is:"+errMsg);
								Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
								break;
							}
						}
						else
						{	
							Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);
						}
						}
				}
			}
		}
		}
		catch(Exception e){
			Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);

		}

	}


	public void UPI_SuccessTransaction() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			for(int i=1;i<=180;i++)
			{
				//boolean timerState = driver.findElement(By.xpath(SessionTimer)).isDisplayed();
				//driver.findElement(By.xpath(SessionTimer)).isDisplayed()==true && 
				if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php"))
				{					
					//Thread.sleep(10000);
					System.out.println(driver.findElement(By.xpath(SessionTimer)).getText());	
					Thread.sleep(10000);
				}
				else
				{
					break;
				}
			}
					if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php") && driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText().equalsIgnoreCase("fail"))
						{
							Thread.sleep(5000);
							errMsg=driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText();
							Extent_Reporting.Log_Pass("Trasnaction cancelled or failed, Failed receipt is generated", "Error Msg is:"+errMsg);
							Extent_Reporting.Log_report_img("Trasnaction cancelled or failed", "Passed", driver);
						}
				else
				{	
					Cash_paymentSuccessMesg();
				}	
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Repective Error Message is exist", "Error Msg is:"+errMsg, driver);

		}

	}

	public void sessionCancel_errMsg() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Thread.sleep(25000);
			if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php") ||
					driver.findElement(By.xpath("//div[@class='row']")).isDisplayed())
			{
				Extent_Reporting.Log_Fail("UPI doesnt exist", "UPI cross button doesnt exist, verify manually", driver);
				Extent_Reporting.Log_report_img("UPI cross button doesnt exist, verify manually", "Passed", driver);
				Thread.sleep(30000);
				if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php") && driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText().equalsIgnoreCase("fail")) 
				  {
					  errMsg=driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText();
					  	Extent_Reporting.Log_Fail("Failed receipt is generated", "Error Msg is:"+errMsg, driver);
						Extent_Reporting.Log_report_img("As cross button to cancel session is not available Failed receipt is generated without being Session pop-up is cancel using cancelled button", "Passed", driver);	
				  }
			}
			else
			if(Assert.isElementDisplayed(driver, SessionTimer, "Session timer"))
			{
				Extent_Reporting.Log_report_img("Session timer popup is exist", "Passed", driver);	
				if(driver.findElement(By.xpath(UPICrossCancelBtn)).isDisplayed()) 
				{
					Assert.Clickbtn(driver, UPICrossCancelBtn, "Cross cancel button");	
					Assert.waitForPageToLoad(driver);
					errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText();
					if(errMsg.contains("REJECTED. Please try paying using another method?")
						||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
						||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
						||errMsg.contains("FAILED. Please try paying using another method?")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method")
						||errMsg.contains("Payment Failed. Please try paying using another method?"))
					{
						Extent_Reporting.Log_Pass("Repective Error Message is exist", "Error Msg is:"+errMsg);
						Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
					}
					else	
					{	
						Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);
					}
				}
				else
				{
					Extent_Reporting.Log_Fail(UPICrossCancelBtn, "Cancel button doesnt exist verify the same manually.", driver);
					Extent_Reporting.Log_report_img("Cancel button doesnt exist verify the same.", "Failed", driver);	
				}
			}
			else
			{	
				Extent_Reporting.Log_Fail("Session popup window does not exist", "Error Msg is:"+errMsg, driver);
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);

		}

	}

	public void UPIDecline_errMsg() throws Exception{
		try{
			boolean flage = false;
			for(int i=1;i<=180;i++)			
			{
				Thread.sleep(2000);
				if(Assert.isElementDisplay(driver, SessionTimer)==false)
				{
					Extent_Reporting.Log_report_img("Session timer popup doesnt exist", "Passed", driver);	
					Thread.sleep(1000); 
					flage = true;
				}
				else
				{
					Extent_Reporting.Log_report_img("Session popup exist", "Passed", driver);	
					Thread.sleep(1000); 
					flage = false;
				}
				if(flage==true)
				{
					Assert.waitForPageToLoad(driver);
					if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php")&&driver.findElement(By.xpath("/html/body/div")).isDisplayed())
					{
						Extent_Reporting.Log_Pass("Failed receipt is generated", "Error Msg is:"+errMsg);
						Extent_Reporting.Log_report_img("Failed receipt is generated", "Passed", driver);	
						break;
					}
					else if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/"))
					{
						WebElement hiddenDiv = driver.findElement(By.xpath(CardInvalidErrMsgVerify));
						errMsg = hiddenDiv.getText(); // does not work (returns "" as expected)
						String script = "return arguments[0].innerText";
						errMsg = (String) ((JavascriptExecutor) driver).executeScript(script, hiddenDiv);			
						System.out.println(errMsg);
						if(errMsg.contains("REJECTED. Please try paying using another method?")
							||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
							||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
							||errMsg.contains("FAILED. Please try paying using another method?")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method"))
						{
							Extent_Reporting.Log_Pass("Repective Error Message is exist", "Error Msg is:"+errMsg);
							Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);	
							break;
						}
					}
					else
					{	
						Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);
					}	
				}
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Repective Error Message does not exist", "Error Msg is:"+errMsg, driver);

		}

	}

	public void UPI_Invalid_ErrMessages() throws Exception{
		try{	
			Thread.sleep(5000);
			String errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getAttribute("value");
			System.out.println(errMsg);
			if(errMsg.contains("REJECTED. Please try paying using another method?")
					||errMsg.contains("We are sorry but the transaction failed. Try paying using another method?") 		                    
					||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
					||errMsg.contains("Credit Card Number is Empty")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method")){	
				Extent_Reporting.Log_Pass("Repective Error Message is exist", "Error Msg is:"+errMsg);
				Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);		    	 
			}else{

				Extent_Reporting.Log_Fail("Respective error Message does not exist", "Failed", driver);
			}

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Respective error Message does not exist", "Failed", driver);
			Log.error("Respective error Message does not exist");
			e.printStackTrace();
			throw new Exception("Respective error Message does not exist");
		}
	}

	public void CaseSensitiveValidation(String xobject,String TypeOFData) throws Exception{
		try{

			Assert.Check_Validation_NoSpecialChar_Alphabetic(driver, xobject,
					TypeOFData, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "CardNumber");		
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void CaseSensitiveValidationForAmexCard(String TypeOFData) throws Exception{
		try{
			System.out.println("CardName"+ CardName);
			if(CardName.contains("amex"))
			{
				Assert.Check_Validation_NoSpecialChar_Alphabetic(driver, CreditCardNoInput,
						TypeOFData, Excel_Handling.Get_Data(TC_ID, "AMEXCardNO").trim(), "CardNumber");	
			}else{

				Extent_Reporting.Log_Fail("You have entered other than amex card", "Failed", driver);
			}
		}catch(Exception e)	
		{
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public static String CardName = null;
	public void Credit_cardValidation() throws Throwable{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Credit card Number input field");						  			  
			Assert.inputText(driver, CreditCardHolderName, Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit   card Number Exp Date");
			CardName = driver.findElement(By.xpath(CreditCardHolderName)).getAttribute("data-pri-type");
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit   card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Credit_LogoValidations(String CardInputXpath) throws Throwable{
		try{
			String[] AllCardNumbers = Excel_Handling.Get_Data(TC_ID, "AllCreditCardNumbers").split(";");
			int Size = AllCardNumbers.length;
			for(int i=0;i<=Size-1;i++)
			{	
				Assert.inputText(driver, CardInputXpath, AllCardNumbers[i].trim(), "Credit card Number");						  			  
				String ActualCardNum = driver.findElement(By.xpath(CardInputXpath)).getAttribute("data-pri-type");	            
				if(ActualCardNum.contains("amex")){ 				
					String First_two = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 2);
					//AllCardNumbers[i].substring(2);
					System.out.println("Fisrt_SixDigit"+First_two);
					int val = Integer.parseInt(First_two);
					if((val==37)||(val==34)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("Amex card bin rangeis not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("dinersclub") ){
					String First_three = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 3);
					//AllCardNumbers[i].substring(3);
					String First_two = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 2);
					//AllCardNumbers[i].substring(2);
					System.out.println("Fisrt_SixDigit"+First_three);
					int val =  Integer.parseInt(First_three);
					int valtwo =Integer.parseInt(First_two);
					if((val==300)||(val==301)||(val==302)||(val==303)||(val==304)||(val==305)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else if((valtwo==36)||(valtwo==38)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}
					else{
						Extent_Reporting.Log_Fail("Diners Club card bin range is not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("jcb")){
					String Fisrt_SixDigit = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 6);
					//AllCardNumbers[i].substring(6);
					System.out.println("Fisrt_SixDigit"+Fisrt_SixDigit);
					int val = Integer.parseInt(Fisrt_SixDigit);
					if((val==353011)||(val==356600)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("JCB card bin range is not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("visa")){
					String First_one = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 1);
					//AllCardNumbers[i].substring(1);
					System.out.println("Fisrt_SixDigit"+First_one);
					int val = Integer.parseInt(First_one);
					if((val==4)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("Visa card bin range is not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("mastercard")){

					String Fisrt_two = driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 2);
					//Assert.inputText(driver, CardInputXpath, AllCardNumbers[i].trim(), "Credit card Number");						  			  
					//String Fisrt_two =  AllCardNumbers[i].substring(2);
					System.out.println("Fisrt_SixDigit"+Fisrt_two);
					int val = Integer.parseInt(Fisrt_two);
					if((val==22)||(val==51)||(val==52)||(val==53)||(val==54)||(val==55)||(val==23)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("Master card bin range is not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("discover")){
					String Fisrt_four =  driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 4);
					System.out.println("Fisrt_four"+Fisrt_four);
					int val = Integer.parseInt(Fisrt_four);
					if((val==6011)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("Master card bin range is not valid", "Failed", driver);
					}
				}else if(ActualCardNum.contains("rupay")){
					String Fisrt_four =  driver.findElement(By.xpath(CardInputXpath)).getAttribute("value").substring(0, 4);
					System.out.println("Fisrt_four"+Fisrt_four);
					int val = Integer.parseInt(Fisrt_four);
					if((val==6521)){
						Extent_Reporting.Log_Pass("Eneterd card logo is: "+ActualCardNum, "Passed");
						Extent_Reporting.Log_report_img("Entered card", "Snap", driver); 
					}else{
						Extent_Reporting.Log_Fail("Master card bin range is not valid", "Failed", driver);
					}
				}
				else{

					Extent_Reporting.Log_Fail("Entered card is invalid", "Failed", driver);	            	
				}	
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Card input field does not exist", "Failed", driver);   
			Log.error("Test failed due to Card input field does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Card input field does not exist");
		}
	}


	public void Amex_Credit_cardValidation() throws Throwable{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "AMEXCardNO").trim(), "Credit card Number input field");						  			  
			Assert.inputText(driver, CreditCardHolderName, Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit   card Number Exp Date");
			Thread.sleep(5000);
			CardName = driver.findElement(By.xpath(CreditCardNoInput)).getAttribute("data-pri-type");
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit   card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public static String ErrorVal =null;
	public static String ErrorLenght = null;
	public void Credit_CardCVVRedError(String CVVcode,String Makepaybtn) throws Exception{
		try{			
			Assert.inputText(driver, CVVcode, "A11", "two digit");
			Assert.Clickbtn(driver, Makepaybtn, "Credit Make Paymnet button");
			ErrorVal = driver.findElement(By.xpath(CVVcode)).getAttribute("data-rule-required");
			ErrorLenght = driver.findElement(By.xpath(CVVcode)).getAttribute("value");

			int Errlength = ErrorLenght.length();
			System.out.println("CVV Vale"+ Errlength);
			if(ErrorVal.contains("false"))
			{
				if(ErrorVal.isEmpty()){
					Extent_Reporting.Log_Pass("Card Cvv code is empty", "Red Line is exist as expected");
					Extent_Reporting.Log_report_img("Card CVV code is empty", "Passed", driver);	
				}else{
					Extent_Reporting.Log_Pass("It enters only "+Errlength+" digit" , "Red Line is exist as expected");
					Extent_Reporting.Log_report_img("Respective Screen print", "passed", driver);
				}						
			}else{				
				Extent_Reporting.Log_Fail("Might be you have entered correct Cvv Code", "Please provide the less cvv for negative testing", driver);					
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Credit cvv field does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Credit_CardExpiryDateErrorRedLine(String xobject,String makebtn) throws Exception{
		try{
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, makebtn, "cvv"))
			{
				Assert.inputText(driver, xobject, "1111", "Eneterd Past date");				
				Assert.Clickbtn(driver, makebtn, "Credit Make Paymnet button");
				ErrorVal = driver.findElement(By.xpath(xobject)).getAttribute("data-rule-required");
				ErrorLenght = driver.findElement(By.xpath(xobject)).getAttribute("value");

				int Errlength = ErrorLenght.length();
				System.out.println("CVV Vale"+ Errlength);
				if(ErrorVal.contains("false"))
				{
					if(ErrorVal.isEmpty()){
						Extent_Reporting.Log_Pass("Card Expiry date is empty", "Red Line is exist as expected");
						Extent_Reporting.Log_report_img("Card Expiry date is empty", "Passed", driver);	
					}else{
						Extent_Reporting.Log_Pass("It enters only "+Errlength+" digit" , "Red Line is exist as expected");
						Extent_Reporting.Log_report_img("Respective Screen print", "passed", driver);
					}						
				}else{				
					Extent_Reporting.Log_Fail("Might be you have entered correct Card Expiry date", "Please provide the less Card Expiry date for negative testing", driver);					
				}				
				Assert.inputText(driver, xobject, "11", "Eneterd Past date");
				Assert.Clickbtn(driver, makebtn, "Credit Make Paymnet button");
				ErrorVal = driver.findElement(By.xpath(xobject)).getAttribute("data-rule-required");
				ErrorLenght = driver.findElement(By.xpath(xobject)).getAttribute("value");

				int Errlength1 = ErrorLenght.length();
				System.out.println("CVV Vale"+ Errlength);
				if(ErrorVal.contains("false"))
				{
					if(ErrorVal.isEmpty()){
						Extent_Reporting.Log_Pass("Card Expiry date is empty", "Red Line is exist as expected");
						Extent_Reporting.Log_report_img("Card Expiry date is empty", "Passed", driver);	
					}else{
						Extent_Reporting.Log_Pass("It enters only "+Errlength1+" digit" , "Red Line is exist as expected");
						Extent_Reporting.Log_report_img("Respective Screen print", "passed", driver);
					}						
				}else{				
					Extent_Reporting.Log_Fail("Might be you have entered correct Card Expiry date", "Please provide the less Card Expiry date for negative testing", driver);					
				}
			}else{
				Extent_Reporting.Log_Fail("Credit cvv field does not exist", "Failed", driver);
			}			 		
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Credit card Expiry date field does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}



	public void Cash_payment() throws Exception{
		try{
			Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
			Thread.sleep(5000);

			driver.getTitle();
			keyboard.pressKey(Keys.F12);
			keyboard.releaseKey(Keys.F12);
			Assert.inputText(driver, CashPinCode, Excel_Handling.Get_Data(TC_ID, "Pin_Code").trim(), "Cash payment pin code");
			AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
			obj.SurchargeForCommonFunctionNotclickplus();
			Assert.Clickbtn(driver, CashMakePayment, "Credit Card make payment button");  
			URL connectURL = new URL("http://localhost/airpay_php/responsefromairpay.php");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connectURL.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			Assert.analyzeLog(driver);

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Cash_payment_Success() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, CashMakePayment, "Cash payment pin code"))
			{
				Assert.inputText(driver, CashPinCode, Excel_Handling.Get_Data(TC_ID, "Pin_Code").trim(), "Cash payment pin code");
				Extent_Reporting.Log_report_img("PinCode Entered", "Passed", driver);
				AmountBlockFetchData();
				Assert.Clickbtn(driver, CashMakePayment, "Credit Card make payment button");  
			}else{
				Extent_Reporting.Log_Fail("Cash Pincode field does not exist", "Failed", driver);
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Pincode field does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}


	public void UTR_payment() throws Exception{
		try{
			Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
			Thread.sleep(5000);
			driver.getTitle();
			keyboard.pressKey(Keys.F12);
			keyboard.releaseKey(Keys.F12);
			Assert.inputText(driver, UTRCode, Excel_Handling.Get_Data(TC_ID, "UTRCode").trim(), "UTR Unique code ");
			//AmountBlockFetchData();
			AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
			obj.SurchargeForCommonFunctionNotclickplus();
			Assert.Clickbtn(driver, UTRCashMakePayment, "UTR make payment");    
			Assert.analyzeLog(driver);			
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public void Cash_paymentSuccess() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Thread.sleep(5000);
			if(Assert.isElementDisplay(driver, CashSuccessTransaction)){
				Assert.isElementDisplayed(driver, CashSuccessTransaction, "cash payment Success Message");
				Extent_Reporting.Log_report_img("Success payment Transaction Message is displayed", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}

	public static String  AirPaytransactionID = null;

	public void Cash_paymentSuccessMesg() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Thread.sleep(10000);			
			if(Assert.isElementDisplay(driver, CashSuccessTransaction)){
				List<WebElement> tblcontent = driver.findElements(By.xpath("(//table[2]/tbody/tr/td[1])"));
				for(int i=1;i<=tblcontent.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[1])["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("TRANSACTIONID:")){
						//Extent_Reporting.Log_Pass("Actual Transaction ID Name Is: "+tblrowVal, "Expected Transaction Id Name is: "+"TRANSACTIONID:");
						String TxnExpectedID = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[2])["+i+"]")).getText().trim();
						if(TxnExpectedID.equalsIgnoreCase(AirPay_PaymentPage_BusinessLogic.GetOrderID)){
							Extent_Reporting.Log_Pass("Actual Transaction ID is: "+TxnExpectedID, "Expected Transaction Id Is: "+AirPay_PaymentPage_BusinessLogic.GetOrderID.trim());
							Extent_Reporting.Log_report_img("Success payment Transaction ID is as expected", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Id Field row Does not exist", "Failed", driver);
					}					
				}

				List<WebElement> tblAmout = driver.findElements(By.xpath("(//table[2]/tbody/tr/td[1])"));
				for(int i=1;i<=tblAmout.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[1])["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("AMOUNT:")){
						//Extent_Reporting.Log_Pass("Actual Amount field Name Is: "+tblrowVal, "Expected Amount field Name is: "+"AMOUNT:");
						String TxnAmt = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[2])["+i+"]")).getText().trim();
						System.out.println("dd"+TxnAmt);
						if(TxnAmt.contains(Excel_Handling.Get_Data(TC_ID, "Amount").trim())||TxnAmt.contains(AirPay_MA_Panel_Select_Merchant_BusinessLogic.MINADDONE)
								||TxnAmt.contains(TotAmt)){
							Extent_Reporting.Log_Pass("Actual Transaction Amount is: "+TxnAmt, "Passed");
							Extent_Reporting.Log_report_img("Success payment Transaction Amount is as expected", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Amount Field row Does not exist", "Failed", driver);
					}					
				}				
				List<WebElement> TranMesg = driver.findElements(By.xpath("(//table[2]/tbody/tr/td[1])"));
				for(int i=1;i<=TranMesg.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[1])["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("MESSAGE:")){
						String TxnMessage = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[2])["+i+"]")).getText().trim();
						if(TxnMessage.equalsIgnoreCase("Success")){
							Extent_Reporting.Log_Pass("Actual Transaction TxnMessage is: "+TxnMessage, "Passed");
							Extent_Reporting.Log_report_img("Success payment Transaction Message is displayed", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Amount Field row Does not exist", "Failed", driver);
					}					
				}

				List<WebElement> ApTransaction = driver.findElements(By.xpath("(//table[2]/tbody/tr/td[1])"));
				for(int i=1;i<=ApTransaction.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[1])["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("APTRANSACTIONID:")){
						AirPaytransactionID = driver.findElement(By.xpath("(//table[2]/tbody/tr/td[2])["+i+"]")).getText().trim();
						Extent_Reporting.Log_Pass("Airpay Transaction ID is as : "+AirPaytransactionID, "Passed");
						Extent_Reporting.Log_report_img("Success payment Transaction Message is displayed", "Passed", driver);
						break;

					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Amount Field row Does not exist", "Failed", driver);
					}					
				}
			}else{
				Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}

	public void Cash_paymentSuccessRedLineError() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplay(driver, CashPinCode)){
				String RedLineError = driver.findElement(By.xpath(CashPinCode)).getAttribute("data-rule-required");
				//developer need to update html code over here!!
				Assert.Clickbtn(driver, CashMakePayment, "Credit Card make payment button");    
				if(RedLineError.isEmpty()){
					Extent_Reporting.Log_Pass("Pin code red line error is exist as expected", "Passed");
					Extent_Reporting.Log_report_img("Red line error screen print", "Passed", driver);

				}else if(RedLineError.contains("false")){
					Extent_Reporting.Log_Pass("Pin code red line error is exist as expected", "Passed");
					Extent_Reporting.Log_report_img("Red line error screen print", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Might be you have provided correct pin code", "Failed", driver);
					throw new Exception("Test failed due to local host page not displayed");
				}
			}else{
				Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}


	public void Cash_UTRRedLineError() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Assert.Clickbtn(driver, UTRCashMakePayment, "UTR make button");    
			if(Assert.isElementDisplay(driver, CashPincodeErrLine)){
				Assert.isElementDisplayed(driver, CashPincodeErrLine, "UTR Red line Error is exist");
				Extent_Reporting.Log_Pass("Pin code red line error is exist as expected", "Passed");
				Extent_Reporting.Log_report_img("Red line error screen print", "Passed", driver);

			}else{
				Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}


	public void UPIErrorMSgForALL() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Assert.Clickbtn(driver, UPICommonPayment, "UPi Common make button");    
			if(Assert.isElementDisplay(driver, UPICommoneErrLine)){
				Assert.isElementDisplayed(driver, UPICommoneErrLine, "UPI Red line Error is exist");
				Extent_Reporting.Log_Pass("UPI Address red line error is exist as expected", "Passed");
				Extent_Reporting.Log_report_img("UPI Address error screen print", "Passed", driver);

			}else{
				Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
				throw new Exception("UPI Address Red Line Error does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
			Log.error("UPI Address Red Line Error does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Red Line Error does not exist");
		}
	}
	
	public void UPIErrorMSgForOther() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Assert.Clickbtn(driver, UPIMakePayment, "UPi Common make button");    
			if(Assert.isElementDisplay(driver, CashPincodeErrLine)){
				Assert.isElementDisplayed(driver, CashPincodeErrLine, "UPI Red line Error is exist");
				Extent_Reporting.Log_Pass("UPI Address red line error is exist as expected", "Passed");
				Extent_Reporting.Log_report_img("UPI Address error screen print", "Passed", driver);

			}else{
				Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
				throw new Exception("UPI Address Red Line Error does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
			Log.error("UPI Address Red Line Error does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Red Line Error does not exist");
		}
	}
	
	
	public void Cash_UPIRedLineError() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Assert.Clickbtn(driver, UPIMakePayment, "UPI make button");    
			if(Assert.isElementDisplay(driver, CashPincodeErrLine)){
				Assert.isElementDisplayed(driver, CashPincodeErrLine, "UPI Red line Error is exist");
				Extent_Reporting.Log_Pass("UPI Address red line error is exist as expected", "Passed");
				Extent_Reporting.Log_report_img("UPI Address error screen print", "Passed", driver);

			}else{
				Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
				throw new Exception("UPI Address Red Line Error does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Red Line Error does not exist", "Failed", driver);
			Log.error("UPI Address Red Line Error does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Red Line Error does not exist");
		}
	}

	public static String errMsg;
	public void Invalid_UPIAddress() throws Exception{

		try{
 				Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, UPIAddIcon, "UPI Field")){
				Assert.inputText(driver, UPIAddressField, Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim(), "UPI Address field");
				Extent_Reporting.Log_report_img("UPI Address detail entered", "Passed", driver);
				/*
				 * if(driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText().
				 * equalsIgnoreCase("")) { errMsg =
				 * driver.findElement(By.xpath(CardInvalidErrMsgVerify)).getText();
				 * System.out.println(errMsg); } else
				 */
				{
					Assert.Clickbtn(driver, UPIMakePayment, "UPI make button");    
					Thread.sleep(5000);
				}
			}else{
				Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
				throw new Exception("UPI Address Field Does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
			Log.error("UPI Address Field Does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Field Does not exist");
		}
	}


	public void Invalid_UPIAddress_Popup(String UserID ) throws Exception{

		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, UPIAddressField, "UPI Red line Error is exist")){
				Assert.inputText(driver, UPIAddressField, UserID, "UPI Address field");
				Extent_Reporting.Log_report_img("UPI Address detail entered", "Passed", driver);
				Assert.Clickbtn(driver, UPIMakePayment, "UPI make button");    
				Assert.waitForPageToLoad(driver);
			}else{
				Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
				throw new Exception("UPI Address Field Does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
			Log.error("UPI Address Field Does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Field Does not exist");
		}
	}
	
	
	
	public void Invalid_UPIAddress_Common(String UserID) throws Exception{

		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, UPICommonPayment, "UPI Red line Error is exist"))
			{
				Assert.inputText(driver, UPIAddressCommonField, UserID, "UPI Address field");
				Extent_Reporting.Log_report_img("UPI Address detail entered", "passed", driver);
				Assert.Clickbtn(driver, UPICommonPayment, "UPI make button");  
				Assert.waitForPageToLoad(driver);
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
			Log.error("UPI Address Field Does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Field Does not exist");
		}
	}
	
	public void Gpay_Common(String UserID) throws Exception{

		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, UPICommonPayment, "UPI Red line Error is exist")){
				Assert.inputText(driver, UPIAddressCommonField, UserID, "UPI Address field");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, TezDomainName, Excel_Handling.Get_Data(TC_ID, "TezDomain").trim(), "Bank Domain name");
				Extent_Reporting.Log_report_img("UPI Address detail entered", "assed", driver);
				Assert.Clickbtn(driver, UPICommonPayment, "UPI make button");    
				Assert.waitForPageToLoad(driver);
			}else{
				Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
				throw new Exception("UPI Address Field Does not exist");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Field Does not exist", "Failed", driver);
			Log.error("UPI Address Field Does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Field Does not exist");
		}
	}
	
	public void ErrorPopupMsgClikcbtn() throws Throwable{

		try{
			Assert.waitForPageToLoad(driver);
			//System.out.println("testings");
			Thread.sleep(15000);
/*			if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php") && driver.findElement(By.xpath("//div[@class='errorwrap']")).isDisplayed())
			{
				Extent_Reporting.Log_Pass("Redirected to invalid url, Kindly verify manually", "Error Msg/ URL is: "+driver.getCurrentUrl());
				Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);			
				throw new Exception("Error Popup does not exist");
			}*/
			if(driver.getCurrentUrl().equalsIgnoreCase("https://payments.airpay.co.in/upi/directindex.php") ||
					driver.findElement(By.xpath("//div[@class='row']")).isDisplayed())
			{
				Extent_Reporting.Log_Fail("UPI doesnt exist", "UPI cross button doesnt exist, verify manually", driver);
				Extent_Reporting.Log_report_img("UPI cross button doesnt exist, verify manually", "Passed", driver);
				Thread.sleep(30000);
				if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php") && driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText().equalsIgnoreCase("fail")) 
				  {
					  errMsg=driver.findElement(By.xpath("//table[@width='100%']/tbody[1]/tr[6]/td[3]")).getText();
					  	Extent_Reporting.Log_Fail("Failed receipt is generated", "Error Msg is:"+errMsg, driver);
						Extent_Reporting.Log_report_img("As cross button to cancel session is not available Failed receipt is generated without being Session pop-up is cancel using cancelled button", "Passed", driver);	
				  }
			}
			else
				if(Assert.isElementDisplayed(driver, PopupErrmShbBtn, "Popup is"))
				{
					Extent_Reporting.Log_report_img("UPI Address detail entered", "passed", driver);
					Assert.Javascriptexecutor_forClick(driver, PopupErrmShbBtn, "Error Message");    
				}
				else
					{
						Extent_Reporting.Log_Fail("Error Popup does not exist", "Failed", driver);
						throw new Exception("Error Popup does not exist");
					}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UPI Address Field Does not exist or UPI redirected to invalid url.", "Failed", driver);
			Log.error("UPI Address Field Does not exist");
			e.printStackTrace();
			throw new Exception("UPI Address Field Does not exist");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void Popular_UPI_Wallet_Verification() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selecetd", "passed", driver);	
						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void Popular_UPI_Wallet_VerificationErrMsg() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
						AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
						obj.SurchargeForCommonFunctionNotclickplus();
						UPIErrorMSgForALL();
						
					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selecetd", "passed", driver);
							AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
							obj.SurchargeForCommonFunctionNotclickplus();
							UPIErrorMSgForALL();
						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
							AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
							obj.SurchargeForCommonFunctionNotclickplus();
							UPIErrorMSgForALL();
						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
							AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
							obj.SurchargeForCommonFunctionNotclickplus();
							UPIErrorMSgForALL();
						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
							AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
							obj.SurchargeForCommonFunctionNotclickplus();
							UPIErrorMSgForALL();
						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
							AirPay_Payment_Mode_Debit_Card_BusinessLogic obj = new AirPay_Payment_Mode_Debit_Card_BusinessLogic(driver, TC_ID); 
							obj.SurchargeForCommonFunctionNotclickplus();
							UPIErrorMSgForOther();
						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	
	public void Card_InvalidMesgVerify() throws Exception{
		try{	
			Thread.sleep(15000);
			if(driver.getCurrentUrl().equalsIgnoreCase(CardInvalidURLVerify))
			{
				String errMsg = driver.findElement(By.xpath(CardInvalidErrMsgVerify1)).getText();
				if(errMsg.contains("Transaction Operation Failed - Card No, not valid. Card Number is Invalid")					            
						||errMsg.contains("Transaction Operation Failed - Card No, not valid. Card Number Verification Failed") 		                    
						||errMsg.contains("Please use a valid debit card issued in india")|| errMsg.contains("Improper Card Name Entered")
						||errMsg.contains("Credit Card Number is Empty")||errMsg.contains("We are sorry but the transaction failed. Try paying using another method")
						||errMsg.contains("This card is not valid for selected bank.")
						||errMsg.contains("This card is invalid for selected bank")
						||errMsg.contains("Authentication/Authorization Failed - Bank not Configured."))
				{
					//String errMsg1 = driver.findElement(By.xpath(CardInvalidErrMsgVerify1)).getText();
					Extent_Reporting.Log_Pass("Redirected to error page", "Error Msg is: "+errMsg);
					Extent_Reporting.Log_report_img("Respective Error Message is exist", "Passed", driver);
					Thread.sleep(1800);
					if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/responsefromairpay.php"))
					{
						Extent_Reporting.Log_Pass("Receipt is generated", "Receipt generated");
						Extent_Reporting.Log_report_img("Redirected to Receipt page", "Passed", driver);
					}
				}	
			}
			else if(driver.getCurrentUrl().equalsIgnoreCase("https://directpayments.nowpay.co.in/#"))
			{
				
				List<WebElement> errorvalue=driver.findElements(By.xpath("//div[contains(@class,'errorvalue')]"));
				for (int i=0; i<errorvalue.size(); i++)
				{
					WebElement errorhelp=errorvalue.get(i).findElement(By.xpath("//span[@class='help']"));
					//System.out.println(errorhelp.getText());
					Extent_Reporting.Log_Pass("Error is shown with highlighted red line", "Error is shown with highlighted red line");
				}
			}
		}
			catch(Exception e)	
			{
				Extent_Reporting.Log_Fail("Respective error Message does not exist", "Failed", driver);
				Log.error("Respective error Message does not exist");
				e.printStackTrace();
				throw new Exception("Respective error Message does not exist");
			}
	}

	
	public void Cash_RTGS_And_NEFT() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplay(driver, UTRCode)){
				Assert.Clickbtn(driver, UTRCashMakePayment, "UTR make button");    
				// there is an issue with developer side in html code.
				String RedLineError = driver.findElement(By.xpath(UTRCode)).getAttribute("data-rule-required");
				if(RedLineError.isEmpty()){
					Extent_Reporting.Log_Pass("UTR field red line error is exist as expected", "Passed");
					Extent_Reporting.Log_report_img("Red line error screen print", "Passed", driver);
				}else if(RedLineError.contains("false")){
					Extent_Reporting.Log_Pass("UTR field red line error is exist as expected", "Passed");
					Extent_Reporting.Log_report_img("Red line error screen print", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Might be you have provided correct pin code", "Failed", driver);
					throw new Exception("Test failed due to local host page not displayed");
				}
			}else{
				Extent_Reporting.Log_Fail("UTR Page field doen not exist", "Failed", driver);
				throw new Exception("Failed due to UTR page does not exist");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("UTR Page field doen not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}

	/**
	 * @author parmeshwar Sakole
	 * @throws Throwable 
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 */
	public void Popular_UPI_Wallet_InvalidUserIDVerification() throws Throwable {
		try{ 
			AirPay_PaymentPage_BusinessLogic AirPay_Local = new AirPay_PaymentPage_BusinessLogic(driver, TC_ID);

			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
						Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
						sessionInvalidUserID();
						ErrorPopupMsgClikcbtn();
						AirPay_Local.Card_Details_Options();

					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selectd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionInvalidUserID();
							ErrorPopupMsgClikcbtn();
							//AirPay_Local.Card_Details_Options();

						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionInvalidUserID();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();


						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionInvalidUserID();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();


						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionInvalidUserID();						
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();

						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Popup(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionInvalidUserID();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();

						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exist",	"Failed",driver);
			Log.error("Test failed due to pay method does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * @throws Throwable 
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 */
	public void Popular_UPI_Wallet_TimeOutSessionNegativeErrMsg() throws Throwable {
		try{ 
			AirPay_PaymentPage_BusinessLogic AirPay_Local = new AirPay_PaymentPage_BusinessLogic(driver, TC_ID);

			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
						Gpay_Common(Excel_Handling.Get_Data(TC_ID, "GpayUserID").trim());
						sessionTimeOut_errMsg();						
						ErrorPopupMsgClikcbtn();
						AirPay_Local.Card_Details_Options();				

					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "BHIMUserID").trim());
							sessionTimeOut_errMsg();
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				
						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PaytmUserID").trim());
							sessionTimeOut_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				
						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PhonepeUserID").trim());
							sessionTimeOut_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				

						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "MobiKwikiUserID").trim());
							sessionTimeOut_errMsg();						
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				

						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Popup(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionTimeOut_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				

						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	/**
	 * @author parmeshwar Sakole
	 * @throws Throwable 
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 */
	public void Popular_UPI_Wallet_TimeOutSessionCancelBtnClickErrMsg() throws Throwable {
		try{ 
			AirPay_PaymentPage_BusinessLogic AirPay_Local = new AirPay_PaymentPage_BusinessLogic(driver, TC_ID);

			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
						Gpay_Common(Excel_Handling.Get_Data(TC_ID, "GpayUserID").trim());
						sessionCancel_errMsg();						
						ErrorPopupMsgClikcbtn();
						
						AirPay_Local.Card_Details_Options();				

					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "BHIMUserID").trim());
							sessionCancel_errMsg();
							ErrorPopupMsgClikcbtn();
							//AirPay_Local.LocalHostDetailPage();
							AirPay_Local.Card_Details_Options();				

						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PaytmUserID").trim());
							sessionCancel_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				


						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PhonepeUserID").trim());
							sessionCancel_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				


						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "MobiKwikiUserID").trim());
							sessionCancel_errMsg();						
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				

						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Popup(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							sessionCancel_errMsg();	
							ErrorPopupMsgClikcbtn();
							AirPay_Local.Card_Details_Options();				

						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * @throws Throwable 
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 */
	public void Popular_UPI_Wallet_DeclineTransactionErrMsg() throws Throwable {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, PopulaUPIWallets, "Select UPI wallet" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(PopulaUPIWallets));			
				for(int i =0;i<popular1.size();i++)
				{
					driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).click();
					String popularbackName = driver.findElement(By.xpath(PopulaUPIWallets+"["+(i+1)+"]")).getText().trim();
					System.out.println("popularbackName"+popularbackName);
					if(popularbackName.contains("tez"))
					{
						Extent_Reporting.Log_Pass("Gpay wallet selected", "Passed");
						Extent_Reporting.Log_report_img(" Gpay wallet selected", "Passed", driver);
						Gpay_Common(Excel_Handling.Get_Data(TC_ID, "GpayUserID").trim());
						UPIDecline_errMsg();						
						ErrorPopupMsgClikcbtn();
						AirPay_PaymentPage_BusinessLogic AirPay_Local = new AirPay_PaymentPage_BusinessLogic(driver, TC_ID);
						AirPay_Local.Card_Details_Options();				

					}else{
						
						String UpiAttribute = driver.findElement(By.xpath("//div[@class='upi-bank']")).getAttribute("data-upi-bank").trim();
						if(UpiAttribute.contains("@upi") && popularbackName.contains("bhim"))
						{
							Extent_Reporting.Log_Pass("Bhim Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("bhim selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "BHIMUserID").trim());
							UPIDecline_errMsg();
							ErrorPopupMsgClikcbtn();
						}
						else if(UpiAttribute.contains("@paytm") && popularbackName.contains("paytm")){
							Extent_Reporting.Log_Pass("paytm Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("paytm wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PaytmUserID").trim());
							UPIDecline_errMsg();	
							ErrorPopupMsgClikcbtn();

						}
						else if(UpiAttribute.contains("@ybl") && popularbackName.contains("phonepe")){
							Extent_Reporting.Log_Pass("phonepe Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("phonepe wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "PhonepeUserID").trim());
							UPIDecline_errMsg();	
							ErrorPopupMsgClikcbtn();

						}
						else if(UpiAttribute.contains("@ikwik") && popularbackName.contains("mobiKwik")){
							Extent_Reporting.Log_Pass("mobiKwik Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("mobiKwik wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Common(Excel_Handling.Get_Data(TC_ID, "MobiKwikiUserID").trim());
							UPIDecline_errMsg();						
							ErrorPopupMsgClikcbtn();
						}
						else if(popularbackName.contains("other")){
							Extent_Reporting.Log_Pass("UPI other Wallet selected", "Passed");
							Extent_Reporting.Log_report_img("UPI other wallet selecetd", "passed", driver);
							Invalid_UPIAddress_Popup(Excel_Handling.Get_Data(TC_ID, "UPI_Address").trim());
							UPIDecline_errMsg();	
							ErrorPopupMsgClikcbtn();
						}		
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	


}
