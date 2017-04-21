#liste de catalogue des gamex,
#par exemple:
#game de réflexion(益智类), aventure(冒险类), action(动作类), fighting(格斗类), action_aventure(动作冒险), simulation(仿真类), stratégie(策略类), cartes(卡牌类), course(运动类), gamex de rôle(角色类), famille(家庭类), musique(音乐类), sports(体育类)
create table category (
cat_id int auto_increment primary key,       #identité du catalogue 
cat_name varchar(20) not null default'',     #nom du catalogue 
intro varchar(200) not null default ''       #introduction du catalogue 
)engine InnoDB charset utf8;


#liste of jeu vidéo 
create table game (
  game_id int unsigned not null auto_increment primary key,      #identité du jeu vidéo
  cat_id smallint(6) not null default '0',
  game_name varchar(30) not null default '',                     #nom de jeu vidéo
  price decimal(10,2) not null default '0.00',                   #le prix de jeu vidéo
  game_number smallint(6) not null default '1',                  #le nombre de jeu vidéo
  click_count  mediumint(9) not null default '0',                #le nombre de visites
  game_intro  text not null,                                     #la introduction de jeu vidéo
  game_img  varchar(30) not null default '',                     #la photo de jeu vidéo
  is_on_sale  tinyint(4) not null default '1',                   #si le jeu vidéo est à vendre ou pas
  is_best  tinyint(4) not null default '0',                      #si le jeu vidéo est le best-seller
  is_new  tinyint(4) not null default '0',                       #si le jeu vidéo est le nouveau
  is_hot  tinyint(4) not null default '0',                       #si le jeu vidéo est le hot-vente
  add_time  int(10) unsigned not null default '0',               #la date de publication
  console_type varchar(20) not null default ''                   #type de console de jeu vidéo
) engine InnoDB charset utf8;


#Table de client  
create table user (
  user_id int unsigned not null auto_increment primary key,   #identité du client
  username varchar(20) not null default '',                   #nom d'usager
  email varchar(30) not null default '',                      #émail
  passwd char(32) not null default '',                        #mot de passe
  last_name  varchar(20) not null default '',                 #nom de client
  first_name varchar(20) not null default ''                  #prénom de client
) engine InnoDB charset utf8;



#table de la commande
create table `order` (
  order_id int unsigned not null auto_increment primary key,  #identité de la commande
  receiver varchar(20) not null default '',                   #nom de destinataire
  address varchar(50) not null default '',                    #adresse du client
  city varchar(30) not null default '',                       #ville
  country varchar(30) not null default '',                    #pays
  zipcode char(6) not null default '',                        #code postal
  receiver_email varchar(40) not null default '',             #mail de destinataire
  tel varchar(20) not null default '',                        #numéro de téléphone de destinataire
  delivery_time varchar(10) not null default '',              #l'horaire de la livraison
  order_amount decimal(10,2) not null default 0.00,           #montant total des ordres
  pay varchar(10) not null default ''                         #paiement
)engine InnoDB charset utf8;



#table pour la relation entre la commande et la liste de jeu vidéo
create table ordergame (
  og_id int unsigned auto_increment primary key,    #identité
  order_id int unsigned not null default 0,         #identité de la commande
  game_id  int unsigned not null default 0,         #identité du jeu vidéo
  game_name varchar(30) not null default '',        #nom de jeu vidéo
  game_number smallint not null default 1,          #le nombre de jeu vidéo
  price decimal(10,2) not null default 0.0          #le prix de jeu vidéo
) engine InnoDB charset utf8;












create table category (
  cat_id int auto_increment primary key,
  cat_name varchar(20) not null default'',
  intro varchar(200) not null default ''
)engine InnoDB charset utf8;


create table game (
  game_id int unsigned not null auto_increment primary key,  
  cat_id smallint(6) not null default '0',
  game_name varchar(30) not null default '',                     
  price decimal(10,2) not null default '0.00',                  
  game_number smallint(6) not null default '1',               
  click_count  mediumint(9) not null default '0',               
  game_intro  text not null,                                    
  game_img  varchar(30) not null default '',                    
  is_on_sale  tinyint(4) not null default '1',                 
  is_best  tinyint(4) not null default '0',                     
  is_new  tinyint(4) not null default '0',                      
  is_hot  tinyint(4) not null default '0',                      
  add_time  int(10) unsigned not null default '0',              
  console_type varchar(20) not null default ''                 
) engine InnoDB charset utf8;

 
create table user (
  user_id int unsigned not null auto_increment primary key,
  username varchar(20) not null default '',
  email varchar(30) not null default '',
  passwd char(32) not null default '',
  last_name  varchar(20) not null default '',
  first_name varchar(20) not null default ''
) engine InnoDB charset utf8;

#ne marche pas
create table order (
  order_id int unsigned not null auto_increment primary key,
  reciver varchar(10) not null default '',
  address varchar(50) not null default '',
  city varchar(30) not null default '',
  country varchar(30) not null default '',
  zipcode char(6) not null default '',
  receiver_email varchar(40) not null default '',
  tel varchar(20) not null default '',
  delivery_time varchar(10) not null default '',
  order_amount decimal(10,2) not null default 0.0,
  pay varchar(10) not null default ''
  ) engine InnoDB charset utf8;
#marche
create table `order` (
  `order_id` int unsigned not null auto_increment primary key,
  `receiver` varchar(20) not null default '',
  `address` varchar(50) not null default '',
  `city` varchar(30) not null default '',
  `country` varchar(30) not null default '',
  `zipcode` char(6) not null default '',
  `receiver_email` varchar(40) not null default '',
  `tel` varchar(20) not null default '',
  `delivery_time` varchar(10) not null default '',
  `order_amount` decimal(10,2) not null default 0.00,
  `pay` varchar(10) not null default ''
)engine InnoDB charset utf8;


create table ordergame (
  og_id int unsigned not null auto_increment primary key,
  order_id int unsigned not null default 0,
  game_id  int unsigned not null default 0,
  game_name varchar(30) not null default '',
  game_number smallint not null default 1,
  price decimal(10,2) not null default 0.0
) engine InnoDB charset utf8;


insert into category values (1, 'réflexion', 'Il y a des jeux de réflexion.');
select * from category;
delete from category where cat_id = 1;




