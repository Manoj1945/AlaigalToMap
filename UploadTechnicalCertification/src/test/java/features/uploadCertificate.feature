Feature: Login as student and upload three Technical certificates. Login into admin page and aprrove one certificate, decline one certificate.
Verify the status of those certificates. 

@studentLogin
Scenario Outline: login as student with the given id & phone number and upload certificate
Given Student should login with the given id and mobile number
When click on co-curricular achievement and click on technical record button
Then fill the details "<Title>" and upload certificate
And click the logout button on student page

Examples:
|Title|
|Project Participation Maplogik|
|Project Participation Tradeviv|
|Project Participation Sreeleathers|

@adminLogin
Scenario: login as admin and approve the technical record
Given login as admin with the given emailId and password
When on admin page click on co-curricular achievement and click on technical record button
Then click "pendingCertificate" technical records button and search the student with the given Id
When find the particular certificate and click approve button
Then click the logout button on admin page

@adminLogin
Scenario: login as admin and decline the technical record
Given login as admin with the given emailId and password
When on admin page click on co-curricular achievement and click on technical record button
Then click "pendingCertificate" technical records button and search the student with the given Id
When find the particular certificate and click decline button
Then click the logout button on admin page

@adminLogin
Scenario: login as admin and verify the pending technical record
Given login as admin with the given emailId and password
When on admin page click on co-curricular achievement and click on technical record button
Then click "pendingCertificate" technical records button and search the student with the given Id
And verify whether the particular certificate is present
Then click the logout button on admin page

@adminLogin
Scenario: login as admin and verify the approved technical record
Given login as admin with the given emailId and password
When on admin page click on co-curricular achievement and click on technical record button
Then click "approveCertificate" technical records button and search the student with the given Id
And verify whether the particular certificate is present
Then click the logout button on admin page

@adminLogin
Scenario: login as admin and verify the declined technical record
Given login as admin with the given emailId and password
When on admin page click on co-curricular achievement and click on technical record button
Then click "declineCertificate" technical records button and search the student with the given Id
And verify whether the particular certificate is present
Then click the logout button on admin page