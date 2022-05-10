insert into users(id, username, password)
values (1, 'aleks', 'korisnik'),
       (2, 'dragica', 'drugarica'),
       (3, 'blaza', 'haker');

insert into hashedUsers(id, username, passwordHash, salt)
values (1, 'bruce', 'qw8Uxa2fXimKruS9wYEm4qm3ZaIGw/hJNvOG3PemhoA=', 'MEI4PU5hcHhaRHZz'),
       (2, 'peter', 'qPWryBEWiWdHsC+67dmO+y5ugGrMVI2w4MSz0+CpDm4=', 'MnY1am14c2d1ZlBf'),
       (3, 'tom', 'FLmYMYmwSRxcy0n2uwysy39ax0TRWvKHswSCPMo+PiI=', 'OChoOitAKWE0TWlD');

insert into persons(id, firstName, lastName, personalNumber, address)
values (1, 'Aleksandar', 'Korisniković', '1203992441123', 'Gotham'),
       (2, 'Dragica', 'Drugarica', '023348574839234', 'Diagon Alley'),
       (3, 'Blaža', 'Hakerić', '3234989332432', 'Bulgaria');

insert into posts(id, picture, text, userId)
values (1, 'llama.jpg', 'A llama I saw on my travel through Peru.', 1);

insert into comments(postId, userId, comment)
values (1, 2, 'Wow, such a majestic llama!!');

insert into roles(id, name)
values (1, 'SELLER'),
       (2, 'BUYER');

insert into user_to_roles(userId, roleId)
values (1, 1),
       (2, 2);

insert into permissions(id, name)
values (1, 'CAR_LIST_VIEW'),
       (2, 'CAR_SEARCH'),
       (3, 'CAR_BUY'),
       (4, 'COMMENT_VIEW'),
       (5, 'COMMENT_ADD'),
       (6, 'CAR_DETAILS_VIEW'),
       (7, 'CAR_DETAILS_EDIT'),
       (8, 'USER_PROFILE_VIEW'),
       (9, 'USER_LIST_VIEW'),
       (10, 'USER_SEARCH'),
       (11, 'USER_PROMOTE_TO_SELLER'),
       (12, 'OWN_PROFILE_VIEW'),
       (13, 'OWN_PROFILE_EDIT'),
       (14, 'CAR_WHOLESALE_PRICE_VIEW');

insert into role_to_permissions(roleId, permissionId)
values (1, 1),
       (2, 1);
