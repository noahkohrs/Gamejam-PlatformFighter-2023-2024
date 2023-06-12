# Fonctionnement

## Ce dépôt GitLab 
1. contient de nombreuses informations sur le projet, ainsi qu'une FAQ
2. vous les lisez, vous y réfléchissez, vous en discutez entre vous
3. si vous avez des questions : j'y réponds en amphi et si nécessaires j'étoffe la FAQ
4. vous pouvez aussi m'envoyer par email vos questions - *claires et bien rédigées* - après avoir vérifiée que la réponse n'est pas sur ce dépôt

## Avec les tuteurs via le journal et le code dans le dépôt

Le chef de projet doit mettre à la racine de la branche `master` un *journal* (fichier `LOG.md`) qui servira d'*outil de plannification* à l'ensemble de l'équipe.
C'est le chef de projet qui le maintient à partir des synthèses rédigées par les responsables des tâches du projet.

`IMPORTANT` **On n'efface rien dans le journal**, on met des tags pour indiquer le statut
- d'un tâche (TODO, DONE, REPORTÉE à <date>, ... )
- d'une question (HORS SUJET, NON RÉSOLU, ...)
- d'une proposition (ABANDONÉE, À TESTER, À IMPLÉMENTER, ...)
 

## Que doit contenir le journal ?

- la date (le jour plus récent en début de fichier)
- une synthèse de l'état d'avancement du projet
  + les blocages
  + les problèmes rencontrés
  + les réponses apportées
- les reflexions en cours
  + les idées (qui propose quoi)
  + les questions en suspend
  + les problèmes
  + les alternatives techniques
- le plan de codage
  + une répartition des tâches qui soit *précise*
    * ce qu'il faut réaliser
    * qui s'en charge
  + les tests prévus pour valider chaque tâche
  + les *points de synchronisation des différentes tâches* et les *tests* qui permettent de les valider

Voici ce qu'il ne faut pas faire
<BLOCKQUOTE>
Lundi: Olivier s'occupe du modèle , Michaël s'occupe des automates
</BLOCKQUOTE>
mais plutôt

---

# EXEMPLE DE JOURNAL du Lundi 13 juin 2020

## Répartition des tâches

- Olivier travaille sur le modèle 
- Michaël travaille sur les automtes

### Olivier + Michael : définition des méthodes qui leur permettront d'interagir
- mettre ici le profil des méthodes

### Olivier (Model)
- création du plateau, d'une entité et de l'action Move.
- Pas de graphisme pour l'instant.

### Michaël (Automaton)
- crée la classe Automaton et une instance qui implémente (sans passer par le parser) un automate qui fait indéfiniment des Move.

## Validation des contributions

### Olivier + Michael

TEST : associer l'automate à l'entité et voir si le Model déclenche l'automate de l'entité.

---


# [SUITE](README.md)

---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 
