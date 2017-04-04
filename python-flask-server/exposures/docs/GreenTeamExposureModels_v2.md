General Exposure Spatial-temporal Exposure Model
================================================

**Goal:** Produce a stratification model for each socioenvironmental and
drug exposure measure in order to compare patient subpopulations based
on drug versus outcome measure (ED visits).

**Dealing with a spatial-temporal exposure model:**<sup>[1]</sup> A patient
will be on a specific drug for a certain time period [Ts,Te]. During
that time period, we can consider they spend a portion of their time at
different locations [L1:D1, L2:D2, ..., LN:DN] where Lx is a location of
interest (geospatial coordinates) and Dx is the percentage of time spent
in that location, n = 1…N. For instance, a patient may spend 50% of time
at work and 50% at home. Ts and Te can be a date or a date and time.

A (socio)environmental exposure estimate for a patient can then be
computed using the input Exp<sub>est</sub> = [Ts,Te,L1:D1,L2:D2,...,:LN:DN]

*Caveat*: This model doesn't explicitly take account of time of day or
time of year, but Exp<sub>est</sub> could be readily be expanded to include those
variables.

*Caveat:* Other comparisons may be made, in addition to drug versus
outcome measure (ED visits).

*Caveat:* The time interval may also involve a specific period of time
prior to diagnosis or adverse event, and we may want to calculate drug
exposure 'scores' during that time interval

*Caveat*: this doesn't handle travel well (e.g., commute routes, or
driving along a road(s) unless the road is considered a location)

From several measures, we will want to calculate daily exposure “scores”
(DES) over the time period [Ts,Te]:

**For example, for PM<sub>2.5</sub>, daily exposure ‘scores’ are:**<sup>[2]</sup>

DES<sub>p</sub>1: 24h max PM<sub>2.5</sub> \< 4.0 μg/m<sup>3</sup>

DES<sub>p</sub>2: 24h max PM<sub>2.5</sub> 4.0-7.06 μg/m<sup>3</sup>

DES<sub>p</sub>3: 24h max PM<sub>2.5</sub> 7.007-8.97 μg/m^3^

DES<sub>p</sub>4: 24h max PM <sub>2.5</sub> 8.98-11.36 μg/m<sup>3</sup>

DES<sub>p</sub>5: 24h max PM<sub>2.5</sub> \> 11.37 μg/m<sup>3</sup>

From the daily scores, we can then calculate exposure scores for a
smaller time interval within [Ts,Te].

**For example, for PM<sub>2.5</sub>, we are interested in calculating overall 7-
and 14-day DES values:**

DES<sub>7p</sub> = (DES**<sub>P</sub>**1+DES**<sub>P</sub>**2…DES**<sub>P</sub>**7)/7

DES<sub>14p</sub> = (DES**<sub>P</sub>**1+DES**<sub>P</sub>**2…DES**<sub>P</sub>**14)/14

*Caveat:* For certain measures (e.g., poverty/non-poverty), the Exp<sub>est</sub>
measure will be the same as the DES and DES<sub>7</sub> and DES<sub>14</sub> values.

1: Exposure to Airborne Particulates
====================================

**Input:** EXP<sub>est</sub>

**Output:** categorical exposure score, ranging from 1 (low) to 5 (high)

In addition to (or instead of) the spatial-temporal exposure model
developed above (and based on [1]), UNC’s CMAS Center has developed
their own models—CMAQ, R-LINE, C-LINE—which provide more accurate and
granular exposure measures. In the near-term, we will work with CMAQ
2010 (36-km resolution) and 2011 (12-km resolution) data on primary and
secondary PM<sub>2.5</sub> and secondary ozone exposures. (Note that ozone
exposures are all secondary.) Eventually, the following exposure
measures will be made available from the CMAS roadside exposure models,
either as yearly averages or as hourly values (which require assumed
conditions on season, time of day, wind direction, atmospheric
stability, day of week, hour of day).

\**Note that PM<sub>2.5</sub> will be the primary (socio)environmental exposure
measure for the Translator Demonstration Use Case because the
PM<sub>2.5</sub>-asthma linkage is well documented and the other exposures will
present the same challenges, so PM<sub>2.5</sub> provides a good stress test of
our model and approach.*

Available measurements:

-   NO<sub>x</sub>: nitrogen oxides; maximum: 200 ppb

-   CO: carbon monoxide; maximum: 10,000 ppb

-   SO<sub>x</sub>: sulfur dioxide; maximum: 100 ppb

-   PM<sub>2.5</sub>: particulate matter with aerodynamic diameter \<2.5 microns;
    maximum: 50 μg/m3

-   Diesel-PM<sub>2.5</sub>: PM<sub>2.5</sub> emissions from diesel vehicles only;
    maximum: 50 μg/m<sup>3</sup>

-   EC<sub>2.5</sub>: portion of PM<sub>2.5</sub> consisting of elemental carbon
    (graphitic carbon and high molecular weight, nonvolatile organic
    compounds); maximum: 50 μg/m<sup>3</sup>

-   OC<sub>2.5</sub>: portion of PM<sub>2.5</sub> consisting of organic carbon
    (particulate organic compounds containing more than 20 carbon
    atoms); maximum: 50 μg/m<sup>3</sup>

-   Benzene: maximum: 30 μg/m<sup>3</sup>

-   Formaldehyde: maximum: 9.8 μg/m<sup>3</sup>

-   Acetaldehyde: (systematic name: ethanol); maximum: 9 μg/m<sup>3</sup>

-   Acrolein: (systematic name: propenal); maximum: 0.02 μg/m<sup>3</sup>

-   1,3-butadiene: maximum: 2 μg/m<sup>3</sup>

5.1: Exposure to Airborne Particulates - PM<sub>2.5</sub>
------------------------------------------------

Description: models are oriented towards two measures, annual mean and
daily max.

Approach: stratify exposure as number of days where max PM<sub>2.5</sub> over all
locations falls into some category, e.g., low, medium, high. Use cutoffs
from [2].

**Daily PM<sub>2.5</sub> exposure ‘score’ (DES<sub>P</sub>):<sup>[2]</sup>**

DES<sub>p</sub>1: 24h max PM<sub>2.5</sub> \< 4.0 μg/m<sup>3</sup>

DES<sub>p</sub>2: 24h max PM<sub>2.5</sub> 4.0-7.06 μg/m<sup>3</sup>

DES<sub>p</sub>3: 24h max PM<sub>2.5</sub> 7.007-8.97 μg/m<sup>3</sup>

DES<sub>p</sub>4: 24h max PM <sub>2.5</sub> 8.98-11.36 μg/m<sup>3</sup>

DES<sub>p</sub>5: 24h max PM<sub>2.5</sub> \> 11.37 μg/m<sup>3</sup>

**Overall 7- and 14-day PM<sub>2.5</sub> risk ‘scores’**

DES<sub>7p</sub> = (DES**<sub>P</sub>**1+DES**<sub>P</sub>**2…DES**<sub>P</sub>**7)/7

DES<sub>14p</sub> = (DES**<sub>P</sub>**1+DES**<sub>P</sub>**2…DES**<sub>P</sub>**14)/14

*Caveat*: These cutoffs are based solely on [2], and they do not account
for extreme weather days. The values are much lower than the US EPA AQI
breakpoints, which provide benchmarks for all persons (children plus
adults, healthy and non-healthy), are skewed toward extreme weather
events, and are not as granular as we propose for pediatric patients
with asthma, who are very sensitive to airborne pollutants. US EPA AQI
breakpoints: 24-h average: 0-12, 12.1-35.4, 35.5-55.4, 55.5-150.4,
150.5-250.4, 250.5-350.4, 350.5-500.4 μg/m<sup>3</sup>
(<https://en.wikipedia.org/wiki/Air_quality_index>)<sup>[3]</sup>. US EPA overall
guidelines are maximum PM<sub>2.5</sub>: 12.0 μg/m<sup>3</sup> over 1 year, 35 μg/m<sup>3</sup>
over 24 hours.

*Caveat:* This approach does not account for exposures prior to the Ts
date, however, we are assuming that the impact of high exposures is
relatively short (\<1 or 2 weeks).

2: Exposure to Ozone
====================

**Input:** EXP<sub>est</sub>

**Output:** categorical ozone exposure score

**Daily ozone exposure ‘score’ (DES<sub>o</sub>):**<sup>[3-5]</sup>

DES<sub>o</sub>1: 24h max ozone ≤ 0.050 ppm

DES<sub>o</sub>2: 24h max ozone 0.051 – 0.075 ppm

DES<sub>o</sub>3: 24h max ozone 0.076-0.100 ppm

DES<sub>o</sub>4: 24h max ozone 0.101-0.125 ppm

DES<sub>o</sub>5: 24h max ozone \> 0.125 ppm

**Overall 7- and 14-day ozone risk ‘scores’**

DES<sub>7o</sub> = (DES**<sub>o</sub>**1+DES**<sub>o</sub>**2…DES**<sub>o</sub>**7)/7

DES<sub>14o</sub> = (DES**<sub>o</sub>**1+DES**<sub>o</sub>**2…DES**<sub>o</sub>**14)/14

*Caveat:* The cutoffs are based on [4], [5], and [6]. As with <sub>PM2.5</sub>,
the ozone exposure cutoffs that we propose are lower than the US EPA AQI
breakpoints US EPA AQI breakpoints: 8-h average: 0-54 ppb, 55-70 ppb,
71-85 ppm 86-105 ppb, 106-200 ppb; 1-h average: 125-164 ppb, 165-204
ppb, 205-404 ppb, 405-504 ppb, 505-604 ppb
<https://en.wikipedia.org/wiki/Air_quality_index><sup>[3]</sup>.

3: Exposure to Crime/Violence
=============================

**Input:** EXP<sub>est</sub>

**Output:** categorical crime exposure score

Crime rates = counts per 1,000 residents of violent crime (murder,
non-negligent manslaughter, forcible rape, robbery, aggravated assault)
or property crime (burglary, larceny-theft, motor vehicle theft),
measured at US Census–tract level

**Daily crime exposure ‘score’ (DES<sub>c</sub>):**<sup>[7]</sup>

1 (less crime) – 10 (more crime) relative to national distribution

**Overall 7- and 14-day crime exposure ‘scores’**

DES<sub>7c</sub> = (DES**<sub>c</sub>**1+DES**<sub>c</sub>**2…DES**<sub>c</sub>**7)/7

DES<sub>14c</sub> = (DES**<sub>c</sub>**1+DES**<sub>c</sub>**2…DES**<sub>c</sub>**14)/14

*Caveat:* The cutoffs in [7] are based on proprietary models. We will
need to identify appropriate cutoffs, perhaps by examining the overall
distribution of crime rates (i.e., across the nation) and then
statistically generating appropriate cutoffs.

4: Exposure to Rural versus Urban Environment
=============================================

**Input:** EXP<sub>est</sub>

**Output:** category, either urban or rural

Rural/urban environment is based on location of home residence and US
Census designation for that location.

If the time period [Ts,Te] crosses multiple years, then use the most
frequent category (e.g., 1 month as urban, 11 months as rural =\> use
rural)

5: Exposure to Poverty
======================

**Input:** EXP<sub>est</sub>, income [optional], age [optional], \# people in
family [optional]

**Output:** category, either below poverty or not

Poverty lines are defined by year based on age (over/under 65 years) and
number of people in the family. Tables for poverty cutoffs can be found
at:
<http://www.census.gov/data/tables/time-series/demo/income-poverty/historical-poverty-thresholds.html>.

*Caveat:* Poverty status may change during the time period [Ts,Te]. This
requires having time-based data on a patient’s income and family size,
which is unlikely. As such, use most recent income and family size data
(even if from different time points).

Age: if unknown, assume \< 65

Income: if unknown, use US Census median income for location

Family size: if unknown, use 4

Poverty:

1 if most recent income \< poverty line for known family size;

0 if otherwise.

6: Exposure to Low Household SES
================================

**Input:** EXP<sub>est</sub>

**Output:** category, one of four values below

SES has multiple definitions. We propose using US Census-tract level
data and the following formulation from [8] and [9] for now. If the time
period [Ts,Te] crosses multiple years, then use the most frequent
category (e.g., 1 month as high SES, 11 months as low SES =\> use low
SES)

Categorize SES based on the percentage of US Census-tract population
living below the poverty line for that year.<sup>[8]</sup>

high SES (0%–4.9%)

upper-middle SES (5.0%–9.9%)

lower-middle SES (10.0%–19.9%)

low SES (≥ 20.0%)

*Caveat:* Many different measures used in literature; for example [9]:
US Census tract-level concentrated disadvantage scores based on 1990 US
Census data was a simple sum of the following: % in poverty + %
unemployed + % female-headed households + (100 - % college graduate).

References and Notes
====================

[1] Spatial-temporal exposure model is based one developed by: Batterman
S, Ganguly R, Isakov V, Burke J, Arunachalam S, Snyder M, Robins T,
Lewis T. Dispersion Modeling of Traffic-Related Air Pollutant Exposures
and Health Effects among Children with Asthma in Detroit, Michigan.
*Transp Res Rec.* 2014l2452:105–112.
<https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4486042/>.

-   Near-roadway EXposure and Urban air pollutant Study (NEXUS) air
    pollution epidemiology study.

-   Summary: exposure based on exposure to carbon monoxide, nitrogen
    oxides, particulate matter under 2.5 microns in diameter (PM2.5),
    and diesel exhaust emissions near roadways.

-   Exposures impact: exacerbation of asthma, asthma onset, impaired
    lung function, card1iovascular morbidity, and mortality, adverse
    birth outcomes, and cognitive decline

-   Relevance: In the US, an estimated 40 million people in the U.S.
    live within 100 m of major roads, railways or airports (*7*) and
    millions more commute on major roads, suggesting the importance of
    exposure to traffic-related air pollutants for public health.

Important notes:

-   Traffic-related air pollutants are elevated levels near roads,
    including PM2.5, ultrafine PM\
    (currently unregulated), VOCs, NO, and polycyclic aromatic
    hydrocarbons (PAHs), and pollutant levels demonstrate steep
    gradients in concentrations and typically reach background levels at
    distances of 150 to 200 m from the road.

-   High traffic/high diesel (HD), defined as homes within 150 m of
    roads with \>6,000 commercial vehicles/day (commercial annual
    average daily traffic; CAADT) and \>90,000 total vehicles/day
    (annual average daily traffic; AADT). High traffic/low diesel (LD),
    defined as homes within 150 m of roads with \>90,000 AADT and
    \<4,500 commercial vehicles/day. Low traffic (LT) homes
    located \>300 m from roads with \>25,000 AADT and greater than 500 m
    from roads with \>90,000 AADT.

-   US DOT roadway data include: number of lanes, roadway type (e.g.,
    freeway, arterial), AADT, average speed, and eight vehicle types
    (heavy duty diesel, light duty gas, …).

[2] Mirabelli MC, Vaidyanathan A, Flanders WD, Qin X, Garbe P. Outdoor
PM<sub>2.5</sub>, ambient air temperature, and asthma symptoms in the past 14
days among adults with active asthma. *Environ Health Perspect*
2016;124(12):1882–1890. <https://www.ncbi.nlm.nih.gov/pubmed/27385358>.

[3] US EPA AQI breakpoints
<https://en.wikipedia.org/wiki/Air_quality_index><sup>[3]</sup>.

[4] White MC, Etzel RA, Wilcox WD, Lloyd C. Exacerbations of childhood
asthma and ozone pollution in Atlanta. *Environ Res.* 1994;65:56–68.
<https://www.ncbi.nlm.nih.gov/pubmed/?term=Exacerbations+of+childhood+asthma+and+ozone+pollution+in+Atlanta>.

-   No effect on hospital visits \<1-h max of 0.110 ppm (110 ppb);
    increased asthma or reactive airway hospital visits day after
    exposure to \>0.110 ppm (110 ppb)

[5] Thurston, G. D.; Lippmann, M.; Scott, M. B.; Fine, J. M. Summertime
haze air pollution and children with asthma. *Am J Respir Crit Care
Med.* 1997;155:654–660. <https://www.ncbi.nlm.nih.gov/pubmed/9032209>.

-   Dose-related increase in asthma med use associated with 1-h max
    ozone: 0, 50, 100, 150, 200 ppb (0, 0.050, 0.100, 0.150, 0.200 ppm)

[6] Sheffield PE, Zhou J, Shmool JLC, Clougherty JE. Ambient ozone
exposure and children’s acute asthma in New York City: a case-crossover
analysis. *Environ Health.* 2015;14:25.
<https://ehjournal.biomedcentral.com/articles/10.1186/s12940-015-0010-2>.

-   Daily average increases of .013 ppm (13 ppb) associated with
    increased risk for asthma ED visit; sex and age interactions; 0- to
    6-day lag period

[7] NeighborhoodScout®, 2000-2016. Location, Inc.

[8] Hines R, Markossian T, Johnson A, Dong F, Bayakly R. Geographic
residency status and census tract socioeconomic status as determinants
of colorectal cancer outcomes. *Am J Public Health* 2014;104(3):e63–e71.
<https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3953793/>.

[9] Freeman VL, Ricardo AC, Campbell RT, Barrett RE, Warnecke RB.
Association of census tract-level socioeconomic status with disparities
in prostate cancer-specific survival. *Cancer Epidemiol Biomarkers Prev*
2011;20(10):2150–2159.
<https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3189295/>.

-   US Census tract-level concentrated disadvantage scores based on 1990
    US Census data was a simple sum of the following: % in poverty + %
    unemployed + % female-headed households + (100 - % college graduate)


