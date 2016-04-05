Who are you talking about?
==============

This project is an example of using the Bluemix platform. Two Bluemix services that are used are Twitter Insights and Relationship Extractor.

This application gets the tweets from a user like @rafaeu and submits to Watson's Relationship Extractor service to retrieve only persons mentioned in this tweets. As final result it retrieves how many times one person is mentioned within the interval informed

To try the application, you can access the url http://rafael-brito-watson-interview.mybluemix.net

You must inform a twitter's user like '@dhh', '@rafaeu'. Remember to cut of '@'. So in this case you have to input 'rafaeu' instead of '@rafaeu'.

The others parameters you have to inform are an initial date and an end date. Remember to follow the pattern CCYY-MM-DD.

Example of a valid request:

Nickname: rafaeu
Initial Date: 2016-02-01
End Date: 2016-04-01

Obs. There's no validation to parameters informed in a wrong way
