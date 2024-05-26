Doubts - 

1) Any example of a particular service?
- HairCut, Carpentry, HouseCleaning

2) Are we considering payments too?
- Yes, we can consider.

3) Do we've 3rd party service executive or in-house?
- Let's say we've both.

4) Is there any constraint on fetching previous services?
- Only Completed


Basic Idea -

**API - CreateService(serviceId, userId, ...)**

Classes/Interfaces -

User
- userId
- phone
- metadata


ServiceType 
[Note: Later we can expand to sub-types like House Cleaning or Pesticide]
- Cleaning 
- Carpentry
- Wellness


ServiceWorkflow
- workflowId
- productsRequired
- process
- description
- numberOfProfessionals
- totalPersonHours


Service
- serviceId
- workflowId
- type
- prices [subscribed/non-subscribed]
- discounts [subscribed/non-subscribed]



Order
- orderId
- serviceId
- userId
- payment
- timeslot
- createdAt
- completedAt
- addOnServices[]



ServiceWorkForce
- orderId [Note: Order will be generated prior to workforce assignment]
- executives[]
- personHours
- perPersonHourBillingAmount



ServiceProfessional
- professionalId
- serviceType
- isThirdParty
- totalBilledAmount
- totalPendingAmount
- metadata


Relations -

1) As user can opt for a service { 1:n }
2) User can place an Order (API-1) { 1:n }
3) A service has multiple types or subtypes { 1:n }
4) Order has service { 1:1 }
5) Order can have addOnServices { 1:n }
6) Order has assigned ServiceWorkForce { 1:1 }
7) Order has payment { 1:1 }
8) ServiceWorkForce has order { 1:1 }
9) ServiceWorkForce can have multiple ServiceProfessional


Possible Applicable Design Patterns

- Builder Pattern for complex data classes
- Adapter Pattern for In-House & 3rd Party Professional
- State Machine for Order State
- To Add more...
