-- liste de catalogue des gamex,
-- par exemple:
-- game de réflexion(益智类), aventure(冒险类), action(动作类), fighting(格斗类), action_aventure(动作冒险), simulation(仿真类), stratégie(策略类), cartes(卡牌类), course(运动类), gamex de rôle(角色类), famille(家庭类), musique(音乐类), sports(体育类)
CREATE TABLE category (
  cat_id INTEGER AUTO_INCREMENT,       #identité du catalogue
  cat_name VARCHAR(20) NOT NULL,     #nom du catalogue
  CONSTRAINT pk_category PRIMARY KEY (cat_id)
) ENGINE InnoDB CHARSET utf8;



# list of consoles
CREATE TABLE console (
  console_id INTEGER,
  console_name VARCHAR(20),
  CONSTRAINT pk_console PRIMARY KEY (console_id)
) ENGINE InnoDB CHARSET utf8;




#liste of jeu vidéo
CREATE TABLE game (
  game_id INTEGER NOT NULL AUTO_INCREMENT,      #identité du jeu vidéo
  game_cat_id INTEGER NOT NULL,                                # catégorie du jeu vidéo
  game_console_id INTEGER NOT NULL,                   #type de console de jeu vidéo
  game_name VARCHAR(50) NOT NULL,                     #nom de jeu vidéo
  game_click_count  BIGINT NOT NULL DEFAULT 0,                #le nombre de visites
  game_summary  TEXT NOT NULL,                                     #la introduction de jeu vidéo
  game_img  TEXT NOT NULL,                           #le lien vers la photo de jeu vidéo
  game_is_on_sale  BOOLEAN NOT NULL DEFAULT FALSE,                   #si le jeu vidéo est à vendre ou pas, taille de 1 (0 ou 1)
  game_sale_rate DECIMAL(3,2) NOT NULL DEFAULT 1.00,
  game_is_best  BOOLEAN NOT NULL DEFAULT FALSE,                      #si le jeu vidéo est le best-seller
  game_is_new  BOOLEAN NOT NULL DEFAULT FALSE,                       #si le jeu vidéo est le nouveau
  game_is_hot  BOOLEAN NOT NULL DEFAULT FALSE,                       #si le jeu vidéo est le hot-vente
  game_add_time  DATETIME NOT NULL,                                  #la date de publication
  CONSTRAINT pk_game PRIMARY KEY (game_id)
) ENGINE InnoDB CHARSET utf8;




# consoles pour un jeu
CREATE TABLE stock_game (
  game_stock_id INTEGER NOT NULL AUTO_INCREMENT,
  game_id INTEGER NOT NULL,
  console_id INTEGER NOT NULL,
  game_stock INTEGER NOT NULL DEFAULT 0,                  #le nombre de jeu vidéo
  game_price DECIMAL(10,2) NOT NULL,                   #le prix de jeu vidéo
  CONSTRAINT pk_console_game PRIMARY KEY (game_stock_id),
  CONSTRAINT uq_game_console UNIQUE (game_id, console_id), -- une seule ligne par console pour un jeu donné
  CONSTRAINT fk_console_game_console FOREIGN KEY (console_id) REFERENCES console(console_id),
  CONSTRAINT fk_console_game_game FOREIGN KEY (game_id) REFERENCES game(game_id)
) ENGINE InnoDB CHARSET utf8;




# catégories pour un jeu
CREATE TABLE category_game (
  game_cat_id INTEGER NOT NULL AUTO_INCREMENT,
  game_id INTEGER NOT NULL,
  cat_id INTEGER NOT NULL,
  CONSTRAINT pk_category_game PRIMARY KEY (game_cat_id),
  CONSTRAINT uq_game_cat UNIQUE (game_id, cat_id),
  CONSTRAINT fk_category_game_category FOREIGN KEY (cat_id) REFERENCES category(cat_id),
  CONSTRAINT fk_category_game_game FOREIGN KEY (game_id) REFERENCES game(game_id)
) ENGINE InnoDB CHARSET utf8;




# table des adresses
CREATE TABLE address (
  adr_id INTEGER AUTO_INCREMENT,
  adr_name VARCHAR(50) NOT NULL,
  adr_street TEXT NOT NULL,
  adr_city VARCHAR(30) NOT NULL,
  adr_country VARCHAR(30) NOT NULL,
  adr_zip_code VARCHAR(30) NOT NULL,
  CONSTRAINT pk_address PRIMARY KEY (adr_id)
);




#Table des users
CREATE TABLE users (
  user_id INTEGER NOT NULL AUTO_INCREMENT,   #identité du client
  user_login VARCHAR(50) NOT NULL,                   #nom d'usager
  user_email VARCHAR(50) NOT NULL,                      #email
  user_passwd VARCHAR(50) NOT NULL,                        #mot de passe
  user_last_name  VARCHAR(20) NOT NULL,                 #nom de client
  user_first_name VARCHAR(20) NOT NULL,                #prénom de client
  user_tel VARCHAR(15),                       # téléphone non obligatoire
  CONSTRAINT pk_user PRIMARY KEY (user_id)
) ENGINE InnoDB CHARSET utf8;



# table des adresses des users, ils peuvent en avoir plusieurs
CREATE TABLE user_adresses (
  user_adr_id INTEGER NOT NULL AUTO_INCREMENT,
  user_id INTEGER NOT NULL,
  adr_id INTEGER NOT NULL,
  is_default_adr BOOLEAN NOT NULL,
  CONSTRAINT pk_user_adresses PRIMARY KEY (user_adr_id),
  CONSTRAINT uq_user_adresses UNIQUE (user_id, adr_id), -- pas de doublons d'adresses pour un user donné
  CONSTRAINT fk_user_adresses_address FOREIGN KEY (adr_id) REFERENCES address(adr_id),
  CONSTRAINT fk_user_adresses_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE InnoDB CHARSET utf8;



# table des paniers d'achats (différent d'une commande)
CREATE TABLE shopping_bag (
  shop_bag_id INTEGER NOT NULL AUTO_INCREMENT,
  shop_bag_user_id INTEGER NOT NULL,
  shop_bag_creation_date DATETIME NOT NULL,
  shop_bag_last_update DATETIME NOT NULL,
  shop_bag_is_bought BOOLEAN, # s'il n'est pas acheté, on peut reconstruire le panier d'un user lors de sa prochaine connexion
  CONSTRAINT pk_shopping_bag PRIMARY KEY (shop_bag_id),
  CONSTRAINT fk_shopping_bag_user FOREIGN KEY (shop_bag_user_id) REFERENCES users(user_id)
) ENGINE InnoDB CHARSET utf8;



# tables des lignes (items) d'un panier d'achat
CREATE TABLE shopping_bag_row (
  shop_bag_row_id INTEGER NOT NULL AUTO_INCREMENT,
  shop_bag_id INTEGER NOT NULL,
  game_stock_id INTEGER NOT NULL,
  nb_units INTEGER NOT NULL DEFAULT 0,
  CONSTRAINT pk_shopping_bag_row PRIMARY KEY (shop_bag_row_id),
  CONSTRAINT uq_shopping_bag_ UNIQUE (shop_bag_id, game_stock_id), -- une seule ligne pour un jeu physique (stock) et un shopping bag donné
  CONSTRAINT fk_shopping_bag_row_game_stock FOREIGN KEY (game_stock_id) REFERENCES stock_game(game_stock_id),
  CONSTRAINT fk_shopping_bag_row_shopping_bag FOREIGN KEY (shop_bag_id) REFERENCES shopping_bag(shop_bag_id)
) ENGINE InnoDB CHARSET utf8;



# commandes effectuées par un utilisateur
CREATE TABLE orders (
  order_id INTEGER NOT NULL AUTO_INCREMENT,
  user_id INTEGER NOT NULL,
  delivery_adress INTEGER, -- adresse par défault de l'user ou nouvelle adresse créée s'il n'en a pas par défaut, possibilité pour lui de choisir ou d'en créer une autre si celle par défaut ne convient pas
  order_date DATETIME NOT NULL,
  shopping_bag INTEGER NOT NULL,
  order_amount DECIMAL(10,2) NOT NULL,
  CONSTRAINT pk_orders PRIMARY KEY (order_id),
  CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT fk_orders_address FOREIGN KEY (delivery_adress) REFERENCES address(adr_id),
  CONSTRAINT fk_orders_shopping_bag FOREIGN KEY (shopping_bag) REFERENCES shopping_bag(shop_bag_id)
) ENGINE InnoDB CHARSET utf8;
