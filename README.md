This is an IOT system simulation app built with microservices.
<br /><b>iot.controller</b> - main data hub and DB storage
<br /><b>iot.simulator</b> - IOT device simulator produces periodical measurements

### API:
- POST   /api/asset
- POST   /api/asset/{id}/start/{intervalSec} - start asset #{id} emitting measurements with {intervalSec}
- POST   /api/asset/{id}/stop - stop asset #{id}
- DELETE /api/asset
---
#### Technology stack: Spring

Communication: HTTP, RabbiMQ (in development)
<br /> Data: JPA
<br /> Build: Maven