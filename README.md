

## Help:
- [SAP IPC Development Help](https://blogs.sap.com/2012/09/26/sap-ipc-development-help/).
- [Steps for developing IPC pricing userexit developement for beginners](https://blogs.sap.com/2012/09/26/steps-for-developing-ipc-pricing-userexit-developement-for-beginners/).
- [SAP Note: 809820 - User exit concept for pricing](https://launchpad.support.sap.com/#/notes/809820).
- Placing existing UserExit IPC code: Download userexits.zip file and extract. The folder "com" from the extracted userexits folder(userexits/com) need to be placed under src of PRC_UE_CUSTOMER(PRC_UE_CUSTOMER/src/com).
- [Change Java complicance Version - For IPC we need 1.4](https://waynebeaton.wordpress.com/2017/03/10/run-eclipse-ide-on-one-version-of-java-but-target-another/).
- [Cannot find the class file for java.lang.Object](https://answers.sap.com/questions/4952877/cannot-find-the-class-file-for-javalangobject.html).
  1. Right click the project in question, go to the Properties.
  2. Go to the Java Build Path Section
  3. Click on the Libraries Tab
  4. Click the Push Button "Add Variable"
  5. Select the entry "JRE_LIB - <path>\jre\lib\rt.jar"
  - This will add all the jre libraries to your project.
  - Next close the project and re-open.
- Get Version of Java "YourClassName.class" file: ""javap -verbose YourClassName | find "version" "". We need version 46.
