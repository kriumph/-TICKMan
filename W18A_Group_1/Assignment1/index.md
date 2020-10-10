 

 

 ![](https://www.labarchives.com/wp-content/uploads/2017/03/university_sydney.png)

 

Agile Software Development Practices (SOFT2412/COMP9412)

School of Computer Science

### Group Project Assignment 1 – Tools for Agile Software Development

 

**Background**

Agile software development is centered around teams and teamwork. In Agile team environments, a set of practices and principles should be followed to guide teams through the development process to build quality software products efficiently and effectively. Implementing Agile practices and principles in a software development project require developing skills that fall in two main areas namely, technical tooling, and social interactions. In this unit, you are required to work in Agile software development teams to experience and develop both technical and social skills.

The goal of the first group project assignment is to work as a team and to use and apply Agile software development tools in the context of a software development project.

 

**Main Requirements**

In this group project, teams of 5-6 students (*must be enrolled in the same tutorial/lab*) will be required to build **a currency converter software application in Java**. All teams are required to use this software project as the starting point of their work. As a team, you will be required to develop specific features/functionalities of the currency converter application.

Following Agile practices, each team is required to work collaboratively and carry out software development activities to build a currency converter application. During the development, each team is required to use the set of software tools covered in this course following Agile development practices. Below are more details of the project requirements and deliverables for the assignment.

 

**Part 1 - Agile Tools Setup**

Each group must collaboratively work to setup a set of software tools that will help them to prepare to prepare agile development environment. All these tools have been/will be introduced the weekly labs including GitHub, Gradle, JUnit (including code coverage) and Jenkins. In your tool setup, you will need to meet the following requirements:

1. There must be a single shared GitHub repository in the supplied [GitHub organization.Links to an external site.](https://github.sydney.edu.au/SOFT2412-2020S2) for the entire group. Every member must contribute to this repository.
2. Gradle must be used for build automation, and JUnit for automated testing. JUnit must be integrated with Gradle and a code coverage tool. For JUnit testing, code coverage must be at least 75%.
3. Continuous integration should be done with Jenkins, which must be hooked with GitHub. Both Gradle and JUnit must be integrated on Jenkins and run automatically for **every new commit** in a particular GitHub branch after Jenkins has been setup correctly.
4. Jenkins must be integrated with GitHub via webhooks. Also, ngrok can be used for continuous integration with Jenkins. Jenkins may be hosted on public cloud service providers if your team is happy with that solution. Jenkins must show test reports for the application. Only one member is required to setup Jenkins.
5. It is recommended to use an IDE like IntelliJ or Eclipse, whatever your group feels comfortable with, to assist you throughout the software development.

The tool setup might become more challenging. So, it is crucial that every group members participate and practice all exercises in the tutorials (week 1 – week 5).

**Part 2 - Building Currency Application using the Agile Tools**

Each group will be required to develop a simple currency converter application in JAVA. All team members must collaboratively build this application using the agile tools they have setup in the previous part. All teams will have to implement following application requirements:

1. There are two user types in the system: admin and normal user (or user). The admin user can use all the functions of the application as specified below. The normal user can use certain functions as specified below.

2. The application should allow normal users to convert money from one currency to another. The application should allow normal users to input money amount and choose its currency symbol and the desired currency they want to convert to. The application should then carry out the conversion and display it with the current currency symbol.

3. Only the admin user can maintain the currency types and its exchange rates. Initially, your application must start with 6 currencies and its exchange rats. Exchange rates must be loaded along with the date when the application first runs. This can be a simple in-memory database (e.g., file). You may find the exchange rates from online sources such as [XE (Links to an external site.)](http://www.xe.com/).

4. The application should allow the normal user to display most popular currencies in a table of the currencies in which every cell represents the exchange rate between the currencies (as shown in table 1). Most popular currencies must be 4 currencies which are specified and updated by the admin user. For example, in the below table, the conversion rate from AUD to SGD is 0.99. Exchange rates must be the most up-to-date rates as specified in the currency rates file/database.

   | **From/To** | **AUD**    | **SGD**    | **US** | **EU** |
   | ----------- | ---------- | ---------- | ------ | ------ |
   | **AUD**     | **-**      | **0.99 ↓** |        |        |
   | **SGD**     | **1.01 ↑** | **-**      |        |        |
   | **US**      |            |            | **-**  |        |
   | **EU**      |            |            |        | **-**  |

   *Table 1. Exchange rates of most popular 4 currencies*

5. The admin user can do all the above operations as well, i.e., convert currencies and display the popular currencies table.

6. The admin user can add new exchange rates daily by entering the date and exchange rate for that date of all currencies stored in the file. A complete history of the change exchange rates must be persistent including the rate and its date of addition. For example, if AUD to SGD was 0.99 and was added on September 4, 2020 and the admin added another exchange rate (e.g., 0.97 on September 5, 2020), these rates must be persisted for the below functionalities.

7. The admin can also add new currency types in addition to the existing currencies and its conversion rates. The most up-to-date currencies should be used in currency conversion and in the most popular currencies table.

8. When the user select to display the 4 x 4 common currencies table, if the new rate has decreased as explained in (6), this must be indicated by a down arrow as shown in *table 1* (i.e. the rate of conversion from AUD to SGD has been decreased compared to previous rate, 0.99 to 0.97).

9. Given the history of the conversion rates (based on admin input), the user can print summary of the conversion rates of 2 currencies they choose within a specific duration (start and end dates). This includes all conversion rates, average, median, maximum, minimum and standard deviation of the conversion rate of the 2 currencies during the specified start and end date.

10. The application must implement a simple user interface to allow users to select from the different available functions (stated above) with clear instructions and messages to guide the user on what should be done, corrected in case of incorrect selection/input and what options available on current or other functions. The user interface **can be** a GUI (java swing, etc.) over command-line based menu and instruction/messages. You are encouraged to have GUI, but not forced to do so.

11. All the above functionality must produce correct output as a result of using the Agile development tools.

12. Wherever certain function behavior or requirement is not specified above, you as a team can decide on how to handle/implement such behaviour as far as that does not lead to any errors or produce inconsistent or wrong output.

Each group must carry on the development of the Currency Application using all the tools they setup in part 1. Teams must demonstrate the proper use of these tools and practices to ensure efficient and effective development as well as delivery of correct application behavior. This includes:

- GitHub collaboration (branching, merging and conflict resolution). Make releases and version your software properly on GitHub.
- Build automation triggers successfully with appropriate reporting
- Automated tests trigger successfully with appropriate test/code coverage and reporting
  - You must make sure your unit tests have a good code coverage. You must write unit test cases that result in more than 75% code coverage.
  - Tests must be such that it covers all edge cases and normal cases as well. Tests must be written for the functionality of the application (listed above).

Each group must also apply relevant continuous integration practices, covered in this course, in their application development.

All groups must show evidence (in their report and demo) of the proper use and application of the above-mentioned agile tools and development/CI practices.

 

**Technical Report**  

Each group must prepare a technical report that record evidence of the above development activities. This specifically includes:

1. Explain how the group collaborated to complete the development of the Currency Application. This should include individual and group contributions, group communication (recorded minutes for all meetings). Each *individual team member* must explain how they contributed towards implementing the features of the application. 
2. A README file **or** a page in the GitHub wiki explaining how to run the program, and how to test it. You may also include other instructions as to how to contribute/collaborate on the codebase.
3. GitHub collaboration. You must explain how GitHub was used for building the application. This might include Project boards, issues, pull requests etc. and how they were used in favor of completing the implementation of the application.
4. Explain how Gradle was used. This may include any extra tasks/dependencies used for the application including brief comments on how those extra tasks/dependencies helped you build the application.
5. Code coverage report for the final commit, including JUnit results for the commits. All Junit test cases that are written must be documented and explained (i.e. why the unit test was chosen and what does it test (regular input, edge cases etc). Document any tests that may have failed. You will also need to explain what the test coverage report is displaying.

 

**Project Demonstration** 

- Each group must demonstrate their project work. You will have to demo your group work and individual contributions to your tutor during week 7 tutorials.
- Each group will have 15-20 minutes to demonstrate a summary of the currency application functionalities and work done through your GitHub repo, automated builds, automated test and Jenkins. 
- All team members must be present in the project demo. Also, every member must demo their individual contributions to the project besides the group work. Your tutor will ask you questions on the entire collaboration including tool setup and implementation. Every team member will be asked questions about the development work done by the team and by themselves.

More details about the demos will be announced closer to the submission deadline.

 

**Group Member Contribution**

If members of your group do not contribute sufficiently you should alert your tutor as soon as possible. The course instructor has the discretion to scale the group’s mark for each member as follows:

| **Level of Contribution** | **Proportion of final grade received** |
| ------------------------- | -------------------------------------- |
| No contribution           | 0%                                     |
| poor/partial contribution | 1% - 49%                               |
| Partial/poor contribution | 50%-99%                                |
| Full contribution         | 100%                                   |

 

### **Deliverable and Submission Guideline**

- Each team must submit their technical report as PDF (one submission per group) through the provided link this Canvas page. All group members must sign the [assignment coversheet](https://canvas.sydney.edu.au/courses/25875/files/11631467/download?wrap=1)[![Preview the document](https://canvas.sydney.edu.au/images/preview.png)](https://canvas.sydney.edu.au/courses/25875/files/11631467/download?wrap=1) and attach it as the first page of the technical report. (reports that does not include the signed assignment cover sheet will not be marked).
- Each team must submit their latest source code version of the Currency Application as zip file with all your project files. A separate submission link will be supplied). 

### **Marking Guide** 

The below marking criteria should help you to prepare and structure your group project report and demo besides the above assignment requirements. 

1. **Quality of Version Control of the Application Source Code (GitHub) (15%)**

Clear and sensible explanation of the work carried out by the team (what, how and why). This should be demonstrated through the project report and the group demo. including: 

- Collaboration model of GitHub repository (from GitHub organization). Every member must have contributed to the Currency application development through this repository. 
- GitHub setup and git  commands
- Examples of representative of GitHub; e.g., merging, branching, and conflict resolutions
- Examples  of the steps to deal with these issues in the previous point 
- How the group collaborated using GitHub to complete the project 

All the above must be supported by appropriate evidences where applicable (e.g., screenshots, outputs, logs). The GitHub organization will be audited during the demo and while marking the report to ensure consistency. 

1. **Quality of Build Automation (Gradle) (10%)**

Clear and sensible explanation of the work carried out by the team (what, how and why) to automate the Currency Application build. This should be demonstrated through the project report and group demo including: 

- Relevant Gradle commands used
- Brief explanation of the build.gradle file 
- Explanation about the results/outputs obtained from relevant Gradle commands 

All the above must be supported by appropriate evidences where applicable (e.g., screenshots, outputs, logs). 

 

1. **Quality of Testing (Junit) (20%)**

Clear and sensible explanation of the work carried out by the team (what, how and why) to automate unit tests. This should be demonstrated through the project report and group demo including: 

- How unit tests are run 
- How code (test) coverage is incorporated
- JUnit test files describing test cases to test the program 
- How and why test cases were designed
- Representative results/output from JUnit tests 
- Results/output from code/test coverage 

All the above must be supported by appropriate evidences where applicable (e.g., screenshots, outputs, logs)

 

1. **Quality of Continuous Integration (Jenkins) (25%)**

Clear and sensible explanation of the work carried out by the team (what, how and why) to automate the CI pipeline. This should be demonstrated through the project report and group demo including: 

- How Jenkins integrated with GitHub
- Build and testing coverage on Jenkins 
- Jenkins setup including webhook, automatic build/test, Jacoco test report displayed on Jenkins, and Jenkins archived outputs (jar and test results) 
- Explanation of representative Jenkins outputs 
- Adopted CI practices

All the above must be supported by appropriate evidences (e.g., screenshots, outputs, logs). 

 

1. **Quality of Application Development (20%)**

Clear and sensible explanation of the work carried out by the team (what, how and why) to implement the requirements of the Currency Application. This should be demonstrated through the project report and group demo including: 

- Description about how the group collaborated to complete the application using the above tools set up, individual and group contributions, group communication (you can reference other sections/parts if already presented previously in your report)
- Overview of the Currency Application design (e.g., class diagrams, sequence diagrams, or any format)
- How the Currency application produces correct output/behaviour with various inputs and adheres to the functional requirements during the demo (recorded and the live Q&A)

All the above must be supported by appropriate evidences where applicable (e.g., screenshots, outputs, logs). 

 

1. **Quality of Demonstration (10%)**

- The demo correctly covers the key important scenarios listed above
- The Currency application adheres to the functional requirements and produces correct output during the live Q&A
- The team is able to sensibly answer questions, explain and justify the work they’ve done in the project, and the results produced during the live Q&A

 

1. **Quality of individual contribution**

- Each team member made fair and enough contribution to the setup of Agile tools and to the development of the Currency application. All members collaborated and worked as a team. This is clearly evident by the GitHub repository and provided evidences in each of the above development activities.
- Each team member should clearly indicate their contribution in the report (table of roles and contributions and reference to the sections in the report)
- The mark for each member will be scaled based on their contributions to the project as per the contribution percentages described in the assignment requirements (0%-100%)

 

## Academic honesty

While the University is aware that the vast majority of students and staff act ethically and honestly, it is opposed to and will not tolerate academic dishonesty or plagiarism and will treat all allegations of dishonesty seriously.

Further information on academic honesty, academic dishonesty, and the resources available to all students can be found on the academic integrity pages on the current students website: https://sydney.edu.au/students/academic-integrity.html.

Further information for on research integrity and ethics for postgraduate research students and students undertaking research-focussed coursework such as Honours and capstone research projects can be also be found on the current students website: https://sydney.edu.au/students/research-integrity-ethics.html.

### Compliance statement

In submitting this work, I acknowledge I have understood the following:

- I have read and understood the University of Sydney's [Academic Honesty in Coursework Policy 2015](https://sydney.edu.au/policies/showdoc.aspx?recnum=PDOC2012/254&RendNum=0).
- The work is substantially my own and where any parts of this work are not my own I have indicated this by acknowledging the source of those parts of the work and enclosed any quoted text in quotation marks.
- The work has not previously been submitted in part or in full for assessment in another unit unless I have been given permission by my unit of study coordinator to do so.
- The work will be submitted to similarity detection software (Turnitin) and a copy of the work will be retained in Turnitin's paper repository for future similarity checking. Note: work submitted by postgraduate research students for research purposes is not added to Turnitin's paper repository.
- Engaging in plagiarism or academic dishonesty in coursework will, if detected, lead to the University commencing proceedings under the [Academic Honesty in Coursework Policy 2015](https://sydney.edu.au/policies/showdoc.aspx?recnum=PDOC2012/254&RendNum=0) and the [Academic Honesty Procedures 2016](http://sydney.edu.au/policies/default.aspx?mode=glossary&word=Academic+honesty).
- Engaging in plagiarism or academic dishonesty in research-focussed work will lead to the University commencing proceedings under the [Research Code of Conduct 2013](https://sydney.edu.au/policies/showdoc.aspx?recnum=PDOC2013/321&RendNum=0) and the [Academic Honesty Procedures 2016](http://sydney.edu.au/policies/default.aspx?mode=glossary&word=Academic+honesty).
- Engaging another person to complete part or all of the submitted work will, if detected, lead to the University commencing proceedings against me for potential student misconduct under the [University of Sydney (Student Discipline) Rule 2016](http://sydney.edu.au/policies/showdoc.aspx?recnum=PDOC2017/441&RendNum=0).