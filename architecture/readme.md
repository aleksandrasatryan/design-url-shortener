### Back of the envelope estimation

- write operation
  - 10 million URLs are generated per day 
  - 10 million / 24 / 3600 = 120 per second
- read operation
  - 10:1 ratio to write, 120 * 10 = 1200 per second
- storage
  - assuming the service will run for 10 years, 10 million * 365 * 10 = 36,5 billion records
  - assume average URL length is 100
  - 36,5 billion * 100 bytes = 3.65 TB


