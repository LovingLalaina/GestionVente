-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 22 sep. 2022 à 19:16
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hightech_market`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

CREATE TABLE `achat` (
  `id` int(11) NOT NULL,
  `num_user` int(11) NOT NULL,
  `num_materiel` int(11) NOT NULL,
  `qte` int(255) NOT NULL,
  `date_achat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`id`, `num_user`, `num_materiel`, `qte`, `date_achat`) VALUES
(2, 4, 3, 5, '2022-07-23'),
(3, 2, 1, 1, '2022-08-02'),
(7, 6, 2, 1, '2022-08-12'),
(12, 2, 4, 2, '2022-08-16'),
(13, 3, 5, 3, '2022-08-16'),
(14, 5, 3, 20, '2022-08-17'),
(15, 4, 6, 14, '2022-08-17'),
(16, 5, 3, 26, '2022-08-17'),
(17, 5, 3, 26, '2022-08-17'),
(18, 5, 3, 32, '2022-08-17'),
(19, 3, 3, 2, '2022-08-17'),
(20, 3, 2, 1, '2022-09-10');

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE `materiel` (
  `num_materiel` int(11) NOT NULL,
  `design` varchar(256) NOT NULL,
  `pu` int(255) NOT NULL,
  `stock` int(255) NOT NULL,
  `description` text NOT NULL,
  `url_image` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`num_materiel`, `design`, `pu`, `stock`, `description`, `url_image`) VALUES
(1, 'ACER ASPIRE', 4700000, 40, 'Nouveau modele d\'ACER, 4Go de memoire vive dediee, carte graphique Nividia GeForce 540m', 'acer_aspire.jpg'),
(2, 'RTX 3070ti', 15000000, 9, 'Carte graphique dernier cri pour votre ordinateur de bureau gamer, stock limite', 'rtx_3070ti.jpg'),
(3, 'Disque Dur Bureau 3.5', 175000, 70, 'Notre stock de disques dur pour les ordinateurs de bureau, reduction de prix pour 5 achetes', 'disque_dur_bureau.jpg'),
(4, 'UC Core i7 4790', 6975000, 23, 'Unite Centrale perfomante, core i7 4eme generation, pour deux achetes, un clavier offert', 'uc_core_i7_4790.jpg'),
(5, 'Carte mere Fujitsu', 5000000, 31, 'Voici la carte mere pour PC Fujitsu Esprimo E720 E85+ format DT, garantie 6 mois', 'carte_mere_fujitsu_esprimo.jpg'),
(6, 'Souris Predator', 450000, 72, 'Acer Predator Cestus 500, souris filiaire pour gamer, ambidextre, capteur optique 7200 dpi, 8 boutons programmables et retro-eclairage RGB', 'souris_predator.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `num_user` int(11) NOT NULL,
  `nom` varchar(256) NOT NULL,
  `adresse_mail` varchar(256) NOT NULL,
  `num_telephone` varchar(20) NOT NULL,
  `mot_de_passe` varchar(256) NOT NULL,
  `type` enum('client','vendeur','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`num_user`, `nom`, `adresse_mail`, `num_telephone`, `mot_de_passe`, `type`) VALUES
(1, 'ANDRIANTAHIANA Loving Lalaina', 'lovinglalainaa@gmail.com', '+261336103470', 'admin', 'admin'),
(2, 'RANIRISOA James Bond', 'jamesbond007@gmail.com', '+261342192445', 'james', 'client'),
(3, 'Charles Vane', 'Charlesvanee@gmail.com', '+261331422522', 'charles', 'client'),
(4, 'Matthieu', 'Matthieu1024@gmail.com', '+261386542121', '1024', 'client'),
(5, 'Ricardo', 'ricardogonzales@gmail.com', '+261334568874', 'rica', 'client'),
(6, 'jems Ranirisoa', 'ranirisoajames@gmail.com', '+261349992445', '1234', 'client');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`num_materiel`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`num_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achat`
--
ALTER TABLE `achat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `materiel`
--
ALTER TABLE `materiel`
  MODIFY `num_materiel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `num_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
