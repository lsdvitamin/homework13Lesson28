insert into phones (client_id, number_) values
    ((select id from clients where name_ = 'Person1'), '8(950)999-99-01'),
    ((select id from clients where name_ = 'Person2'), '8(950)999-99-02'),
    ((select id from clients where name_ = 'Person3'), '8(950)999-99-03'),
    ((select id from clients where name_ = 'Person4'), '8(950)999-99-04'),
    ((select id from clients where name_ = 'Person5'), '8(950)999-99-05'),
    ((select id from clients where name_ = 'Person6'), '8(950)999-99-06'),
    ((select id from clients where name_ = 'Person2'), '8(950)999-99-07'),
    ((select id from clients where name_ = 'Person3'), '8(950)999-99-08');
