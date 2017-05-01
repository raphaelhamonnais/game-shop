# Changements
#   - pas de default_adress pour la table de relation user_adresses
#   - pas de gale click count, trop lourd
#   - pas de fausse PK pour CATEGORY_GAME
#   - pas de fausse PK pour USER_ADRESSES


-- liste des catégories des jeux,
-- par exemple:
-- game de réflexion(益智类), aventure(冒险类), action(动作类), fighting(格斗类), action_aventure(动作冒险), simulation(仿真类), stratégie(策略类), cartes(卡牌类), course(运动类), gamex de rôle(角色类), famille(家庭类), musique(音乐类), sports(体育类)
CREATE TABLE CATEGORY (
  cat_id INTEGER AUTO_INCREMENT,       #identité du catalogue
  cat_name VARCHAR(20) NOT NULL,     #nom du catalogue
  CONSTRAINT pk_category PRIMARY KEY (cat_id),
  CONSTRAINT uq_category_name UNIQUE (cat_name)
) ENGINE InnoDB CHARSET utf8;



# list of consoles
CREATE TABLE CONSOLE (
  console_id INTEGER AUTO_INCREMENT,
  console_name VARCHAR(20),
  CONSTRAINT pk_console PRIMARY KEY (console_id),
  CONSTRAINT uq_console_name UNIQUE (console_name)
) ENGINE InnoDB CHARSET utf8;




#liste of jeu vidéo
CREATE TABLE GAME (
  game_id INTEGER NOT NULL AUTO_INCREMENT,      #identité du jeu vidéo
  game_name VARCHAR(50) NOT NULL,                     #nom de jeu vidéo
  game_summary  TEXT NOT NULL,                                     #la introduction de jeu vidéo
  game_img  TEXT NOT NULL,                           #le lien vers la photo de jeu vidéo
  game_is_on_sale  BOOLEAN NOT NULL DEFAULT FALSE,                   #si le jeu vidéo est à vendre ou pas, taille de 1 (0 ou 1)
  game_sale_rate DECIMAL(3,2) NOT NULL DEFAULT 1.00,
  game_is_best  BOOLEAN NOT NULL DEFAULT FALSE,                      #si le jeu vidéo est le best-seller
  game_is_new  BOOLEAN NOT NULL DEFAULT FALSE,                       #si le jeu vidéo est le nouveau
  game_is_hot  BOOLEAN NOT NULL DEFAULT FALSE,                       #si le jeu vidéo est le hot-vente
  game_add_time  DATETIME NOT NULL,                                  #la date de publication
  CONSTRAINT pk_game PRIMARY KEY (game_id),
  CONSTRAINT uq_game_name_and_add_time UNIQUE (game_name, game_add_time)
  -- CONSTRAINT uq_game_name_and_summary UNIQUE (game_name, game_summary) -- impossible car TEXT ne peut être indexé dans la contrainte d'unicité
) ENGINE InnoDB CHARSET utf8;




# jeu physique avec console, prix et stock
CREATE TABLE PHYSICAL_GAME (
  physical_game_id INTEGER NOT NULL AUTO_INCREMENT,
  game_id INTEGER NOT NULL,
  console_id INTEGER NOT NULL,
  game_stock INTEGER NOT NULL DEFAULT 0,                  #le nombre de jeu vidéo
  game_price DECIMAL(10,2) NOT NULL,                   #le prix de jeu vidéo
  CONSTRAINT pk_physical_game PRIMARY KEY (physical_game_id),
  CONSTRAINT uq_physical_game UNIQUE (game_id, console_id), -- une seule ligne par console pour un jeu donné
  CONSTRAINT fk_physical_game__console FOREIGN KEY (console_id) REFERENCES CONSOLE(console_id),
  CONSTRAINT fk_physical_game__game FOREIGN KEY (game_id) REFERENCES GAME(game_id)
) ENGINE InnoDB CHARSET utf8;




# catégories pour un jeu
CREATE TABLE CATEGORY_GAME (
  game_id INTEGER NOT NULL,
  cat_id INTEGER NOT NULL,
  CONSTRAINT pk_category_game PRIMARY KEY (game_id, cat_id),
  CONSTRAINT fk_category_game__category FOREIGN KEY (cat_id) REFERENCES CATEGORY(cat_id),
  CONSTRAINT fk_category_game__game FOREIGN KEY (game_id) REFERENCES GAME(game_id)
) ENGINE InnoDB CHARSET utf8;




# table des adresses
CREATE TABLE ADDRESS (
  adr_id INTEGER AUTO_INCREMENT,
  adr_name VARCHAR(50) NOT NULL,
  adr_street VARCHAR(255) NOT NULL,
  adr_city VARCHAR(30) NOT NULL,
  adr_country VARCHAR(30) NOT NULL,
  adr_zip_code VARCHAR(30) NOT NULL,
  CONSTRAINT pk_address PRIMARY KEY (adr_id),
  CONSTRAINT uq_address UNIQUE (adr_name, adr_street, adr_city, adr_country, adr_zip_code)
);




#Table des users
CREATE TABLE USERS (
  user_id INTEGER NOT NULL AUTO_INCREMENT,   #identité du client
  user_login VARCHAR(50) NOT NULL,                   #nom d'usager
  user_email VARCHAR(50) NOT NULL,                      #email
  user_passwd VARCHAR(255) NOT NULL,                        #mot de passe
  user_last_name  VARCHAR(20) NOT NULL,                 #nom de client
  user_first_name VARCHAR(20) NOT NULL,                #prénom de client
  user_tel VARCHAR(15),                       # téléphone non obligatoire
  user_active BOOLEAN DEFAULT TRUE,
  CONSTRAINT pk_user PRIMARY KEY (user_id),
  CONSTRAINT uq_user_login UNIQUE (user_login),
  CONSTRAINT uq_user_email UNIQUE (user_email)
) ENGINE InnoDB CHARSET utf8;



# table des adresses des users, ils peuvent en avoir plusieurs
CREATE TABLE USER_ADRESSES (
  user_id INTEGER NOT NULL,
  adr_id INTEGER NOT NULL,
  CONSTRAINT pk_user_adresses PRIMARY KEY (user_id, adr_id),
  CONSTRAINT fk_user_adresses__address FOREIGN KEY (adr_id) REFERENCES ADDRESS(adr_id),
  CONSTRAINT fk_user_adresses__user FOREIGN KEY (user_id) REFERENCES USERS(user_id)
) ENGINE InnoDB CHARSET utf8;



# table des paniers d'achats (différent d'une commande)
CREATE TABLE SHOPPING_BAG (
  shop_bag_id INTEGER NOT NULL AUTO_INCREMENT,
  user_id INTEGER NOT NULL,
  creation_date DATETIME NOT NULL,
  last_update DATETIME NOT NULL,
  is_bought BOOLEAN, # s'il n'est pas acheté, on peut reconstruire le panier d'un user lors de sa prochaine connexion
  CONSTRAINT pk_shopping_bag PRIMARY KEY (shop_bag_id),
  CONSTRAINT fk_shopping_bag__user FOREIGN KEY (user_id) REFERENCES USERS(user_id),
  CONSTRAINT uq_shopping_bag_user_and_creation_date UNIQUE (user_id, creation_date)
) ENGINE InnoDB CHARSET utf8;



# tables des lignes (items) d'un panier d'achat
CREATE TABLE SHOPPING_BAG_ROW (
  shop_bag_row_id INTEGER NOT NULL AUTO_INCREMENT,
  shop_bag_id INTEGER NOT NULL,
  physical_game_id INTEGER NOT NULL,
  nb_units INTEGER NOT NULL,
  CONSTRAINT pk_shopping_bag_row PRIMARY KEY (shop_bag_row_id),
  CONSTRAINT uq_shopping_bag_ UNIQUE (shop_bag_id, physical_game_id), -- une seule ligne pour un jeu physique (stock) et un shopping bag donné
  CONSTRAINT fk_shopping_bag_row__physical_game FOREIGN KEY (physical_game_id) REFERENCES PHYSICAL_GAME(physical_game_id),
  CONSTRAINT fk_shopping_bag_row__shopping_bag FOREIGN KEY (shop_bag_id) REFERENCES SHOPPING_BAG(shop_bag_id)
) ENGINE InnoDB CHARSET utf8;



# commandes effectuées par un utilisateur
CREATE TABLE ORDERS (
  order_id INTEGER NOT NULL AUTO_INCREMENT,
  user_id INTEGER NOT NULL,
  adr_id INTEGER, -- adresse de livraison
  shop_bag_id INTEGER NOT NULL,
  order_date DATETIME NOT NULL,
  order_amount DECIMAL(10,2) NOT NULL,
  CONSTRAINT pk_orders PRIMARY KEY (order_id),
  CONSTRAINT fk_orders__user FOREIGN KEY (user_id) REFERENCES USERS(user_id),
  CONSTRAINT fk_orders__address FOREIGN KEY (adr_id) REFERENCES ADDRESS(adr_id),
  CONSTRAINT fk_orders_shopping__bag FOREIGN KEY (shop_bag_id) REFERENCES SHOPPING_BAG(shop_bag_id),
  CONSTRAINT uq_orders_shop_bag_id UNIQUE (shop_bag_id) -- un id de panier correspond forcément à une seule commande si le panier a été acheté
) ENGINE InnoDB CHARSET utf8;


CREATE TABLE ROLES (
  role_name VARCHAR(50),
  CONSTRAINT pk_roles PRIMARY KEY (role_name)
) ENGINE InnoDB CHARSET utf8;


CREATE TABLE USER_ROLES (
  user_login VARCHAR(50),
  role_name VARCHAR(50),
  CONSTRAINT pk_user_roles PRIMARY KEY (role_name, user_login),
  CONSTRAINT fk_user_roles__user FOREIGN KEY (user_login) REFERENCES USERS(user_login),
  CONSTRAINT fk_user_roles__roles FOREIGN KEY (role_name) REFERENCES ROLES(role_name)
) ENGINE InnoDB CHARSET utf8;