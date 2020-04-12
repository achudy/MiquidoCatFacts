# MiquidoCatFacts
A repository for the recruitment task for the position of Intern Android Developer at Miquido due 13.04.2020.
## Task
App with 2 screens, one being a list of items, the second - the item's details.
Data is obtained from [Cat Facts public API](https://alexwohlbruck.github.io/cat-facts/docs/).
### Requirements
+ 30 random cat facts are loaded on the list screen,
+ List screen contains a button that triggers list refresh - every click downloads 30 new cat facts,
+ Loading animation is displayed during data fetching,
+ Every item on the list contains a static icon and fact id (icons made myself),
+ Clicking on the specific item redirects to the details screen, and the proper cat fact is fetched,
+ Details screen contains only the fact text and fact update date,
### Tech requirements
+ Application written in Kotlin,
+ Minimum Android SDK version is 5.0 - Lollipop,
+ Remote data is fetched using the [Retrofit2](https://square.github.io/retrofit/) library,
+ The application is stored on a Github repository,
+ Dependency injection is done with [Koin](https://github.com/InsertKoinIO/koin),
+ Background operations are handled with Kotlin coroutines,
+ The pattern used is MVVM.
