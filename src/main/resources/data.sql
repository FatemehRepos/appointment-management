insert into appointment_type (id, title, creator_id, last_modifier_id)
values (1, 'ONLINE', 1, 1),
       (2, 'IN_PERSON', 1, 1),
       (3, 'CHAT', 1, 1);
insert into reservation_type (id, title, creator_id, last_modifier_id)
values (1, 'ONLINE', 1, 1),
       (2, 'BY_RECEPTIONIST', 1, 1);
insert into doctor (id, name, creator_id, last_modifier_id)
values (1, 'فاطمه فتحی', 1, 1);
insert into service (id, title, creator_id, last_modifier_id)
values (1, 'مشاوره تحصیلی', 1, 1);
insert into service (id, title, creator_id, last_modifier_id)
values (2, 'مشاوره ازدواج', 1, 1);
insert into doctor_service (id, doctor_id, service_id, creator_id, last_modifier_id)
values (1, 1, 1, 1, 1);
insert into doctor_service (id, doctor_id, service_id, creator_id, last_modifier_id)
values (2, 1, 2, 1, 1);
insert into clinic (id, name, creator_id, last_modifier_id)
values (1, 'سایان', 1, 1);
insert into patient (id, name, creator_id, last_modifier_id)
values (1, 'فاطمه فتحی', 1, 1);