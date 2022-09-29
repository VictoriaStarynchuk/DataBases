-- 1
select*
from owner
order by name;

-- 2
select*
from business_land
where quantity_station >= 2
order by 3;

-- 3
select station.id, station.area_sq_km, element.panel_quantity, element.battery_quantity
from station, element
where element_id = element.id and area_sq_km < 1000
group by station.id;

-- 4
select avg(solar_amount), exporting
from energy
where exporting is not null;

-- 5
select*
from battery
where type like 's%';

-- 6
select *
from business_land, city
where city.name in ('Kyiv','Kharkiv') and city_id = city.id;

-- 7 
select quantity_station, address, city.name as city, country.name as country
from business_land
inner join city, country where business_land.city_id = city.id and country_id = country.id;

-- 8 
select owner.name, owner.surname, count(business_land.id) as quantity_of_lands
from (owner inner join business_land on owner.id=business_land.id)
group by surname
having count(business_land.id) <= 1;

-- 9 
select id, area_sq_km as area
from station 
where exists (select panel_quantity from element where station.element_id = element.id and battery_quantity < 10);

-- 10
select*
from energy
where energy.id = any
(select energy_id from station where area_sq_km < 1000);
