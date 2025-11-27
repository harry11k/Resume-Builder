insert into app_user(id, user_name, password, active, roles )
values
(1, 'einstein', 'einstein', true, 'ROLE_USER'),
(2, 'newton', 'newton', true, 'ROLE_USER'),
(3, 'penrose', 'penrose', true, 'ROLE_USER'),
(4, 'karthikeyan', 'karthikeyan', true, 'ROLE_USER'),
(5, 'amirtham', 'amirtham', true, 'ROLE_USER');


insert into user_profile (id, user_name, theme, summary, first_name, last_name, email, phone, designation)
values
(1, 'einstein', 1, 'Developed the theory of relativity, one of the two pillars of modern physics. My work is also known for its influence on the philosophy of science.', 'Albert', 'Einstein', 'einstein@gmail.com', '111-111-1111', 'Theoretical physicist'),
(2, 'newton', 2, 'Widely recognised as one of the most influential scientists of all time and as a key figure in the scientific revolution', 'Isaac', 'Newton', 'newton@gmail.com', '222-222-2222', 'Mathematician, physicist, astronomer, theologian, and author'),
(3, 'penrose', 1, 'Developed the Conformal Cyclic Cosmology, one of the pillars of mathematician. My work is also known for its influence on the Consciousness and Quantum Mechanics', 'Roger', 'Penrose', 'penrose@gmail.com', '333-333-3333', 'Mathematician'),
(4, 'karthikeyan', 1, 'Good at Math. Started as Math Teacher and Retired as Head Master', 'Karthikeyan', 'R', 'karthikeyanrajalingam4@gmail.com', '9942883466', 'Head Master'),
(5, 'amirtham', 1, 'Good at Cooking and Students Teacher', 'Amirtham', 'G', 'nalinikarthik31@gmail.com', '9442145431', 'Teacher');