# QAEngineerAssessmentProject Testing Framework

### Workspace preparation
1. Install JDK 17, Maven
2. Install Allure:   
Linux For debian-based repositories a PPA is provided:  
```sudo apt-add-repository ppa:qameta/allure sudo apt-get update sudo apt-get install allure```  
Mac OS X For Mas OS, automated installation is available via Homebrew:   
```brew install allure```  
Windows For Windows, Allure is available from the Scoop commandline-installer. To install Allure, download and install Scoop and then execute in the Powershell:  
```scoop install allure```


Highlights
* BO/PO architecture with fluent test flows
* Thread-safe WebDriver via DriverManager
* Parallel execution via TestNG (parallel="classes")
* SLF4J + Logback logging
* Text normalization for resilient validations
* Retry mechanism for flaky tests
* Allure reporting with step annotations