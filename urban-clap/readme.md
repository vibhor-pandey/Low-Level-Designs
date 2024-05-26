Low Level Design

**Problem Statement**

Create below given APIs, which provides services to users similar to Urban Clap.


1) API : createService(serviceId, userId, ...)
2) API : getPreviousServices(userId)

Flows - 

> Create Service
> 1) isUserAuthorized(TRUE) & get User by Id
> 2) Get Service & workflow by Id
> 3) Check addOn Services
>   - IF TRUE THEN add
> 4) Initiate payment flow (initially TRUE)
> 5) Block timeslot
> 6) Create Order
> 7) Initiate Order State Journey
> 8) Return Order Response
> 9) Trigger Event - Service_Workforce_Assignment


> Payment
> 1) Return True

> OrderStateManager
> 1) Initiate Order State Journey
> 2) Update Order State Journey
> 3) Next State

Events - 

> Block_Time_Slot
> 1) Return Blocked

> Service_Workforce_Assignment
1) Add <OrderId, ServiceId> in Workforce Pool
2) Assign Order to exist free workforce
3) Update Order State Journey


