# Particulate Matter 2.5 (pm25) query documentation

Description:

## pm25 Value Calculation

TODO:

## pm25 Risk Score Calculation

### Spatio-temporal Exposure model:

```Est = [Ts,Te,L1:D1,L2:D2,...,LN:DN],where [Ts,Te] is the time interval of interest, 
Ln  is a location (e.g., home, school), and 
Dn represents the percentage of time a given patient spends in each location, n = 1…N. 
```### Daily PM2.5 risk ‘score’ (DRSP)

```1: 24h max PM2.5 < 4.0 μg/m32: 24h max PM2.5 4.0-7.06 μg/m33: 24h max PM2.5 7.007-8.97 μg/m34: 24h max PM 2.5 8.98-11.36 μg/m35: 24h max PM2.5 > 11.37 μg/m3
```### Overall 7- and 14-day PM2.5 risk ‘scores’

```DRSP7 = (DRSP1+DRSP2…DRSP7)/7DRSP14 = (DRSP1+DRSP2…DRSP14)/14
```