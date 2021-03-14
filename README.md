# supermarket-pricing

A. **Expression du besoin**

Fixation des prix des marchandises dans les supermarchés.
La problématique consiste à expérimenter un modèle qui soit suffisamment souple pour s'adapter à ces systèmes de tarification (et à d'autres), tout en étant généralement utilisable - comment conserver une piste d'audit des décisions de tarification ?


B. **Découpage du besoin en User Stories**

**User Story 1** : En tant que client je dois pouvoir acheter ce que je veux. Il n'y a pas de gestion de stocks.

**User Story 2** : En tant que client, je dispose d'un panier, et je suis libre d'acheter les articles que je souhaite, qui sont ensuite ajoutés dans le panier.

**User Story 3** : En tant que client, lorsque je n'ai plus besoin de rien, je dois pouvoir me rendre à la caisse du supermarché, qui se charge de calculer la facture de manière incrémentale, en fonction de chaque article présent dans le panier.

**User Story 4** : Le panier du client est chargé de s'assurer que le client ne tente rien de suspect (par exemple, ajouter une valeur décimale à un article acheté en valeur unitaire).

**User Story 5** : Vérifier que le supermarché n'aurait pas de tels articles et contrôler cela directement dans la méthode d'ajout du panier.

**User Story 6** : En termes de structure, implémenter une sorte de décorateur, qui décide de la méthode de calcul du prix à appliquer à un article, en fonction de ses propriétés.

**User Story 7** : Les fonctionnalités "Trois pour un dollar" et "Achetez-en deux, obtenez-en un gratuitement" doivent être implémentées de manière plus générique dans la seule classe "PackagePricing".

**User Story 8** : Il y a toujours un prix unitaire pour lequel on peut acquérir une unité de l'article désiré. Cela signifie que "trois pour un dollar", est alors un prix spécifique appliqué pour obtenir trois unités spécifiquement, tandis que le "achetez-en deux, obtenez-en un gratuit", est également une réduction de 33 % si l'on obtient trois unités.

**User Story 9** : Toutefois, si on obtient une unité de l'article susmentionné, le prix sera toujours plus élevé puisque la réduction ne sera pas appliquée.

**User Story 9** : Outre la méthode PackagePricing, il existe une méthode DefaultPricing qui inclut le calcul de "1,99 $/livre". La méthode DefaultPricing calculera le prix comme une multiplication plate d'une marge et d'un prix, car le panier du client est chargé d'activer la tarification au poids en fonction de l'article sélectionné.

C. **Frameworks et IDE utilisés pour cette application**

- **Java 8**, pour les développements applicatifs back-end.
- **vavr**, bibliothèque fonctionnelle pour Java 8+ qui fournit des types de données immuables et des structures de contrôle fonctionnelles.
- **JUnit**, pour les tests unitaires 
- **JUnitParams**, bibliothèque qui permet de paramétrer facilement les méthodes de test dans les tests JUnit.
- **IntelliJ IDEA 2020.3**, pour le développement
