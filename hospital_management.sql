# Hospital Management & Patient Analytics System

A MySQL database system to manage patient records, doctor appointments, and treatment data with deep operational analytics.

## Database Schema

| Table | Key Columns |
|-------|-------------|
| Patients | patient_id, name, age, gender, city |
| Doctors | doctor_id, name, specialization, experience_years |
| Appointments | appointment_id, patient_id, doctor_id, date, status |
| Treatments | treatment_id, patient_id, doctor_id, diagnosis, cost, date |

## Analytical Queries Included

1. **Most Consulted Doctors** - Appointment count per doctor
2. **Monthly Revenue** - Treatment cost aggregated by month
3. **Most Common Diagnoses** - Frequency and average cost per disease
4. **Patient Visit Frequency** - Identifies most regular patients
5. **Doctor Performance** - Revenue generated and average cost
6. **High-Risk Patients** - Multiple diagnoses, total treatment cost
7. **Gender Distribution** - Treatment stats by gender
8. **Cancellation Rate** - Per doctor appointment no-show analysis

## Tools Used

- **MySQL** - Relational database
- **ENUM** - Data validation for status and gender
- **Foreign Keys** - Referential integrity
- **GROUP_CONCAT** - Aggregating diagnoses per patient
- **DATE_FORMAT** - Monthly grouping
- **CASE WHEN** - Conditional aggregation
- **HAVING** - Post-aggregation filtering

## Setup

```sql
mysql -u root -p < hospital_management.sql
```
