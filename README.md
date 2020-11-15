## Usage
The input file is in the project root directory. Please run the application with
the name of this file as the only program argument : 'input.csv'

Certain tests are dependant on additional input data, which is in the 'resources' directory.


## Brief
### Trip Charges
Trips between Stop 1 and Stop 2 cost $3.25
Trips between Stop 2 and Stop 3 cost $5.50
Trips between Stop 1 and Stop 3 cost $7.30


completed tripPart - touch on & touch off : can charge amount above
Incomplete tripPart - charged for the most expensive possible tripPart from the stop they touched on at.
Cancelled tripPart - If the passenger touches off at the same stop, they are not charged


### INPUT
ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559

### OUTPUT
Started,               Finished,                DurationSecs,  FromStopId, ToStopId, ChargeAmount, CompanyId, BusID,    PAN,              Status
22-01-2018 13:00:00,   22-01-2018 13:05:00,     900,           Stop1,      Stop2,    $3.25,        Company1,  B37,      5500005555555559, COMPLETED

## Notes
### Assumptions
* Each input file is an individual, for whom we calculate all trips
* Each instance of "ON" followed by and instance of "OFF" will be counted as a complete tripPart
* If an instance of "ON" is followed by another instance of "ON", the most
    expensive tripPart is charged and a new charge is commenced from the next "ON"
* A tripPart is concluded only after a sequence of:
    **  ON, OFF
    **  ON, ON


