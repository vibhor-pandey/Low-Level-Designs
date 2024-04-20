Logging System (LLD)

* Requirements

a) Ability to PUSH logs to one or multiple places/sinks
b) Ability to categorize log level type (INFO, DEBUG, ERROR)
c) Sinks & Category must be configurable

------- Class & Interface Involved -------

* Logger (Public Interface : Singleton)
- createInstance
- log - info, debug, error
- logBelowLevel

* AbstractLogger (InfoLogger, DebugLogger, ErrorLogger)
- level/category/type
- log()

* LogObserver (Interface/Abstract Class)
- addObserver()
- notifyAllObservers()

* InfoLoggerObserver, DebugLoggerObserver, ErrorLoggerObserver


