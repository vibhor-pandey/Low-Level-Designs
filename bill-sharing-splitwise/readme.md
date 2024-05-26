# Problem Statement

## Create a bill sharing app like Splitwise

> ### Requirements
> 1. User should be able to add bill/expense
> 2. User can split bill/expense among other participated users of the expense
> 3. User can create a Group of Users
> 4. User can add bill/expense to the Group
> 5. Bill/Expense in the Group can be divided among all OR participated users of the group
> 6. App can support multiple types of Split Method like EQUAL, EXACT OR PERCENTAGE
> 7. Bill can be settled


> ### Possible Interface APIs
> 1. addExpense(amount, participants, splitType)
> 2. createGroup(name, participants)
> 3. addGroupExpense(amount, participants, splitType)
> 4. payExpense(fromParticipantId, toParticipantId)
> 5. getExpenseByParticipant(participantId)
> 6. getGroupSummary(groupId)

> Note: 
> - payExpense: Settlement of an individual participant to the Expense Creator
> - settleExpense: Expense Settled by the Expense Creator

### Input & Output Format

> Input
> 
> "ExpenseName Amount PaidBy NumberOfParticipants PName:Exact/Percentage SplitType"
> 
> Example:
> 1. "Movie 2000 Vibhor 3 Neeraj Karan Sudheer Equal"
> 2. "Rent 8000 Neeraj 2  Karan:2500 Vibhor:4000 Exact"

> Output
>
> ""

### Classes Required

### Participant
- Name
- Email

### ParticipantManager
- Map: <Name, Participant>
- createParticipant(name, email)
- getParticipant(name)

#### Expense : Data Class
- Name
- TotalAmount
- SplitType
- Map: <Participants, SplitAmount>
- paidBy

### ExpenseManager
- Map : <Participant, Expenses>
- addExpense(participantId, expense)
- payExpense(participantId, expense)
- settleExpense(participantId)

### Group : Data Class
- Name
- Participants: List
- Expense: List

### GroupManager
- Map: <GroupName, Group>
- createGroup(name, participants)
- addMembers(name, participants)
- addGroupExpense(participantId, groupName, expense)

### SplitType: Enum
- EXACT
- EQUAL
- PERCENTAGE

### SplitAmount : Interface
- splitAmount()

### ExactAmount < SplitAmount
- amount
- splitAmount()

### EqualAmount < SplitAmount
- totalParticipants
- splitAmount()


### PercentageAmount < SplitAmount
- percentage
- splitAmount()