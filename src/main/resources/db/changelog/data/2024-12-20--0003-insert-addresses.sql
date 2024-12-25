insert into addresses(client_id, street) values
    ((select id from clients where name_ = 'Person1'), 'Ленина'),
    ((select id from clients where name_ = 'Person2'), 'Зегеля'),
    ((select id from clients where name_ = 'Person3'), 'Цветочная'),
    ((select id from clients where name_ = 'Person4'), 'Энергостроителей'),
    ((select id from clients where name_ = 'Person5'), 'Лесная'),
    ((select id from clients where name_ = 'Person6'), 'Поперечная');