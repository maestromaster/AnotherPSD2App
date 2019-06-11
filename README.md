# AnotherPSD2App
An Android App, that in future can be connected to any of European banks, using their public API.

## ING Api
For now only oAuth and showcase calls are available
https://developer.ing.com/api-marketplace/marketplace/2d00fd5f-88cd-4416-bbca-f309ebb03bfe/reference

## Architecture
The App uses modular MVP architecture, with Koin DI, Retrofit2, RxJava2

## Modules
The App is divided by modules for each feature and function. So each feature can be maintained independently.
![Architecture](/Architecture.jpg)

### TODO List: 
* Fishish oAuth integration
* Fishish Account details and Transactions mock calls
* Add Unit tests
* Add UI tests
