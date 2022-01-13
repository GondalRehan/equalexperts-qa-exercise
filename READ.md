Overview
The purpose of the exercise it to help us understand how you approach both manual and automated testing tasks.
The target of the exercise is a fictitious hotel booking website and can be found here
The website is hosted using a micro AWS instance, which is shared by multiple testers. Please bear this in mind as you test.

Part 1

Manually test the hotel booking system, based upon your own testing intuition (no requirements are provided).
Write a brief summary of the testing you performed, such that another tester could continue your work.

Part 2

Create one or more automated tests to check the creation and deletion of bookings.
Your automated tests should be written in Java, Scala or a .NET language.
Once complete, please package your tests in a way that allows us to instantly see them running.
Please can you confirm your receipt and importantly let me know when you might be able to get this back to me, so we can make sure we have someone to review the exercise.

# 1. Hotel Reservation Form
The 'Hotel Booking Form' is an information management application that runs on the web. Customers can create, verify and delete their booking information on the booking form. Multiple users can process the information in the following fields (Firstname, Surname, Price, Deposit, Check-in, and Check-out).
Functional end-to-end tests are written to cover the basic functionality of the web form.
In-Scope: The application was functionally tested. Nonfunctional Testing was not done for this application because it was out of scope.

# 1. Components and

* Build tool: **Maven**
* Browser Automation: **Selenium WebDriver**
* Test Data: **Page files**

# 2. How to run
The current test only run through the Chrome browser

The test can be run in the local environment. Each environment is linked to maven profiles and has its own configuration file. Properties can be configured in:
'src/main/resources/resources.properties'

* mvn test -Dtest=HotelReservationTest

# 3. Framework Overviews

The Framework has been designed with clear levels of abstraction in mind. pages, context. For page object inialization, use Static Factory methods.

## A. Pages

These include the *Page.java, *ActController and *VerifyController classes. These are the lowest level of
abstraction.

*Page.java:
* These classes contain methods that act directly on the web browser via the Selenium
  WebDriver API.

*ActController.java:
* To separate the logic for page action.  Action methods in the action controller class.

*Verification.java:
* For verification of pages. Verify methods in to verify controller class.

## B. Data Sources
Test data put inside the Page object class

### In future
Expected data are stored in the CSV files.

## Test Coverage
Following two test basic functional test was covered by this Automation test suite.
1. Customers can make confirm and erase Booking
