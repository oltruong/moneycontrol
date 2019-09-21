<template>
    <div class="col-12">

        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="operation-tab" data-toggle="tab" href="#operation" role="tab"
                   aria-controls="home" aria-selected="true">Opération</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="category-tab" data-toggle="tab" href="#category" role="tab"
                   aria-controls="profile" aria-selected="false">Catégories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="#expense" role="tab"
                   aria-controls="contact" aria-selected="false">Détail dépenses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="income-tab" data-toggle="tab" href="#income" role="tab"
                   aria-controls="contact" aria-selected="false">Détail recettes</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="operation" role="tabpanel"
                 aria-labelledby="operation-tab">

                <div class="row">
                    <div class="col-2">
                        <h2>Filtre</h2>
                        <input type="text" v-model="filter">
                        <h5>{{filtered_operations.length}} opération<span
                                v-if="filtered_operations.length > 1">s</span>
                        </h5>
                        <h5>{{sum_filtered_operations}} &euro;</h5>
                        <!--                            <input class="form-control" [(ngModel)]="query">-->
                        <!--                            <h5>{{getOperations().length}} opération<span *ngIf="getOperations().length>1">s</span>-->
                        <!--                            </h5>-->
                        <!--                            <h5>{{sumFiltered() | number:'.2'}} &euro;</h5>-->

                    </div>

                    <div class="col-10">

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col" @click="set_sort_by('creationDate')">Date
                                    <i
                                            class="fas fa-caret-up" v-show="sort_by=='creationDate' && asc"></i><i
                                            class="fas fa-caret-down"
                                            v-show="sort_by=='creationDate' && !asc"></i>
                                </th>
                                <th scope=" col" @click="set_sort_by('name')">Nom
                                    <i class="fas fa-caret-up" v-show="sort_by=='name' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='name' && !asc"></i>
                                </th>
                                <th scope="col" @click="set_sort_by('amount')">Montant
                                    <i class="fas fa-caret-up" v-show="sort_by=='amount' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='amount' && !asc"></i>

                                </th>
                                <th scope="col" @click="set_sort_by('category')">Cat&eacute;gorie
                                    <i class="fas fa-caret-up" v-show="sort_by=='category' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='category' && !asc"></i>

                                </th>
                                <th scope="col" @click="set_sort_by('subcategory')">Ss. Cat.
                                    <i class="fas fa-caret-up" v-show="sort_by=='subcategory' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='subcategory' && !asc"></i>
                                </th>
                                <th scope="col" @click="set_sort_by('recipient')">Destinataire
                                    <i class="fas fa-caret-up" v-show="sort_by=='recipient' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='recipient' && !asc"></i>
                                </th>
                                <th scope="col" @click="set_sort_by('comment')">Commentaire
                                    <i class="fas fa-caret-up" v-show="sort_by=='comment' && asc"></i>
                                    <i class="fas fa-caret-down" v-show="sort_by=='comment' && !asc"></i>

                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="operation of filtered_operations">
                                <td>{{moment(operation.creationDate).format('DD/MM/YYYY')}}</td>
                                <td>{{operation.name}}</td>
                                <td>{{operation.amount}}</td>
                                <td>{{operation.category}}</td>
                                <td>{{operation.subcategory}}</td>
                                <td>{{operation.recipient}}</td>
                                <td>{{operation.comment}}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="category" role="tabpanel" aria-labelledby="category-tab">
                <div class="row">
                    <div class="col-6"><h2>Dépenses</h2>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>Nom</td>
                                <td>Montant</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="category in negative_categories">
                                <td>{{category.name}}</td>
                                <td>{{-category.amount}}</td>
                            </tr>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td>Total</td>
                                <td>{{negative_categories_total}}</td>
                            </tr>
                            </tfoot>
                        </table>

                    </div>
                    <div class="col-6"><h2>Recettes</h2>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>Nom</td>
                                <td>Montant</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="category in positive_categories">
                                <td>{{category.name}}</td>
                                <td>{{category.amount}}</td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td>Total</td>
                                <td>{{positive_categories_total}}</td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

            </div>
            <div class="tab-pane fade" id="expense" role="tabpanel" aria-labelledby="expense-tab">
                <div class="row">
                    <div class="col-12"><h2>Dépenses</h2>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>Catégorie</td>
                                <td>Sous catégorie</td>
                                <td>Montant</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="line in negative_subcategories">
                                <td>{{line.category}}</td>
                                <td>{{line.subcategory}}</td>
                                <td>{{-line.amount}}</td>
                            </tr>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
            <div class="tab-pane fade" id="income" role="tabpanel" aria-labelledby="income-tab">
                <div class="row">
                    <div class="col-12"><h2>Dépenses</h2>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>Catégorie</td>
                                <td>Sous catégorie</td>
                                <td>Montant</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="line in positive_subcategories">
                                <td>{{line.category}}</td>
                                <td>{{line.subcategory}}</td>
                                <td>{{line.amount}}</td>
                            </tr>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>

            <!--                            <table class="table table-striped">-->
            <!--                                <thead>-->
            <!--                                <tr>-->
            <!--                                    <th scope="col">Nom</th>-->
            <!--                                    <th scope="col">Montant</th>-->
            <!--                                </tr>-->
            <!--                                </thead>-->
            <!--                                <tbody>-->
            <!--                                <tr *ngFor="let category of getNegativeCategories()">-->
            <!--                                    <td>{{category[0]}}</td>-->
            <!--                                    <td>{{-category[1]| number:'.2'}}</td>-->
            <!--                                </tr>-->
            <!--                                </tbody>-->
            <!--                            </table>-->
            <!--                        </div>-->
            <!--                        <div class="col-6"><h2>Recettes</h2>-->
            <!--                            <table class="table table-striped">-->
            <!--                                <thead>-->
            <!--                                <tr>-->
            <!--                                    <th scope="col">Nom</th>-->
            <!--                                    <th scope="col">Montant</th>-->
            <!--                                </tr>-->
            <!--                                </thead>-->
            <!--                                <tbody>-->
            <!--                                <tr *ngFor="let category of getPositiveCategories()">-->
            <!--                                    <td>{{category[0]}}</td>-->
            <!--                                    <td>{{category[1]| number:'.2'}}</td>-->
            <!--                                </tr>-->
            <!--                                </tbody>-->
            <!--                            </table>-->
            <!--                        </div>-->
            <!--                    </div>-->
            <!--                </div>-->
            <!--                <div class="tab-pane fade" id="expense" role="tabpanel" aria-labelledby="expense-tab">À-->
            <!--                    venir-->
            <!--                </div>-->
            <!--                <div class="tab-pane fade" id="income" role="tabpanel" aria-labelledby="income-tab">À venir-->
            <!--                </div>-->
        </div>

    </div>

</template>
<style lang="sass">
    tfoot, thead
        font-weight: bold
</style>
<script>
    import moment from 'moment';

    export default {
        name: "OperationsList",
        props: {
            operations: Array
        },
        data: function () {
            return {
                filter: "",
                sort_by: "name",
                asc: true,
            }
        },
        computed: {
            filtered_operations() {
                return this.filtered_operations_by_name().sort((a, b) => {
                    let result = 0;
                    if (this.sort_by === "name") {
                        result = this.wrapper(a.name).localeCompare(this.wrapper(b.name));
                    } else if (this.sort_by === "category") {
                        result = this.wrapper(a.category).localeCompare(this.wrapper(b.category));
                    } else if (this.sort_by === "subcategory") {
                        result = this.wrapper(a.subcategory).localeCompare(this.wrapper(b.subcategory));
                    } else if (this.sort_by === "comment") {
                        result = this.wrapper(a.comment).localeCompare(this.wrapper(b.comment));
                    } else if (this.sort_by === "recipient") {
                        result = this.wrapper(a.recipient).localeCompare(this.wrapper(b.recipient));
                    } else if (this.sort_by === "amount") {
                        result = a.amount - b.amount;
                    }

                    if (result === 0) {
                        result = Date.parse(a.creationDate) - Date.parse(b.creationDate);
                    }
                    return (this.asc ? result : -result);

                });
            },
            sum_filtered_operations() {
                return this.sum_amount(this.filtered_operations_by_name());
            },
            positive_categories() {
                return this.group_by_categories(i => i > 0).sort((a, b) => this.wrapper(a.name).localeCompare(this.wrapper(b.name)));
            },
            positive_categories_total() {
                return this.sum_amount(this.group_by_categories(i => i > 0));
            },
            negative_categories() {
                return this.group_by_categories(i => i <= 0).sort((a, b) => this.wrapper(a.name).localeCompare(this.wrapper(b.name)));
            },
            negative_categories_total() {
                return this.sum_amount(this.group_by_categories(i => i <= 0));
            },
            positive_subcategories() {
                return this.group_by_subcategories(i => i > 0).sort((a, b) => this.wrapper(a.category).localeCompare(this.wrapper(b.category)));
            },
            negative_subcategories() {
                return this.group_by_subcategories(i => i <= 0).sort((a, b) => this.wrapper(a.category).localeCompare(this.wrapper(b.category)));
            }


        },
        created: function () {
            console.log("CREATED");
        },
        updated: function () {
            console.log("UPDATED");
        },
        methods: {
            filtered_operations_by_name() {
                return this.operations.filter(operation => this.operation_matches(operation));
            },
            operation_matches(operation) {
                return this.contains(operation, 'name')
                    || this.contains(operation, 'category')
                    || this.contains(operation, 'subcategory')
                    || this.contains(operation, 'recipient')
                    || this.contains(operation, 'amount')
                    || this.contains(operation, 'comment');
            },
            contains(operation, item) {
                return this.wrapper(operation[item]).toLowerCase().indexOf(this.filter.toLowerCase()) > -1
            },
            set_sort_by(criteria) {

                if (this.sort_by === criteria) {
                    this.asc = !this.asc;
                } else {
                    this.sort_by = criteria;
                }
            },
            wrapper(value) {
                return value ? value.toString() : "";
            },
            groupBy(group, item) {
                return group.reduce(function (rv, x) {
                    (rv[x[item]] = rv[x[item]] || []).push(x);
                    return rv;
                }, {});
            },
            group_by_categories(filter) {
                let categories = this.groupBy(this.operations, 'category');

                let results = [];
                let category;
                for (category in categories) {
                    // console.log(ia);
                    // console.log("sdsd [" + ia + "]");
                    // if (ia === undefined) {
                    //     console.log("AROB");
                    //     ia = "Non classé";
                    // }
                    //
                    //
                    const total = this.sum_amount(categories[category]);
                    if (filter(total)) {
                        results.push({
                            name: category,
                            amount: total
                        });
                    }
                }
                return results;
            },

            group_by_subcategories(filter) {
                let categories = this.groupBy(this.operations, 'category');

                let results = [];
                let category;
                for (category in categories) {

                    let subcategories = this.groupBy(categories[category], 'subcategory');

                    let subcategory;
                    for (subcategory in subcategories) {
                        const total = this.sum_amount(subcategories[subcategory]);
                        if (filter(total)) {
                            results.push({
                                category: category,
                                subcategory: subcategory,
                                amount: total
                            });
                        }
                    }

                }
                return results;
            },
            sum_amount(operation_list) {
                return operation_list.map(i => i.amount).reduce((a, b) => a + b, 0);
            },
            get_operations() {
                console.log("GETTT OPERATIONS");
                console.log(this.$route.params.category);
                console.log(this.$route.params.year);
                console.log(this.$route.params.month);

                console.log("current month" + moment().month());
                // console.log(this.params.year);
                // console.log(this.params.month);

                return [
                    {
                        "id": "5ccdb3c127f60f195d57903c",
                        "creationDate": "2019-05-03T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR SEPA ALAN",
                        "amount": 56.45,
                        "category": "Santé",
                        "subcategory": "Mutuelle Alan",
                        "recipient": "Alan",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57903d",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA SURAVENIR",
                        "amount": -200.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie",
                        "recipient": "Yomoni",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57903e",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA SURAVENIR",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie Eva",
                        "recipient": "Yomoni",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57903f",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA SURAVENIR",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie Nathan",
                        "recipient": "Yomoni",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579040",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA SURAVENIR",
                        "amount": -100.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie",
                        "recipient": "Yomoni",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579041",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "CARTE 30/04/19 44 LECLERC        CB*0975",
                        "amount": -31.37,
                        "category": "Nourriture",
                        "subcategory": "Supermarché",
                        "recipient": "Leclerc Nantes",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579042",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "CARTE 30/04/19 44 FIVES GUYS FRA CB*2981",
                        "amount": -8.5,
                        "category": "Loisirs",
                        "subcategory": "Restaurant",
                        "recipient": "Five Guys",
                        "comment": ""
                    },
                    {
                        "id": "5ccdb3c127f60f195d579043",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "CARTE 01/05/19 44 PETITE BOULANG CB*2981",
                        "amount": -6.83,
                        "category": "Nourriture",
                        "subcategory": "Boulangerie",
                        "recipient": "La Petite Boulangerie",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579044",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA SURAVENIR",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie",
                        "recipient": "Yomoni",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579045",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "CARTE 30/04/19 44 HETM041        CB*0975",
                        "amount": -4.99,
                        "category": null,
                        "subcategory": null,
                        "recipient": null,
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579046",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR SEPA MAISON BLANCHE PAROLE DE VIE",
                        "amount": -90.0,
                        "category": "Dîme",
                        "subcategory": "PDV",
                        "recipient": "Alex & Ingrid",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579047",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR SEPA EGLISE NANTES",
                        "amount": -300.0,
                        "category": "Dîme",
                        "subcategory": "EPENB",
                        "recipient": "EPENB",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579048",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR SEPA Mission et Entraide Bonne Nouve",
                        "amount": -100.0,
                        "category": "Dîme",
                        "subcategory": "Bonne nouvelle Mission",
                        "recipient": "JP & Ely",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d579049",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA PARNASSE-MAIF",
                        "amount": -5.0,
                        "category": "Vie Courante",
                        "subcategory": "Prévoyance Claudia",
                        "recipient": "MAIF",
                        "comment": "Assurance"
                    },
                    {
                        "id": "5ccdb3c127f60f195d57904a",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR SEPA PEA OLIVIER",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "PEA",
                        "recipient": "PEA Olivier",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57904b",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "PRLV SEPA FREE MOBILE",
                        "amount": -15.99,
                        "category": "Charges",
                        "subcategory": "Téléphone",
                        "recipient": "Free",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57904c",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR PERIODIQUE - CEFP",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "CEFP",
                        "recipient": "Boursorama",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57904d",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR PERIODIQUE - ASSURANCE VIE EVA",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie Eva",
                        "recipient": "Boursorama",
                        "comment": null
                    },
                    {
                        "id": "5ccdb3c127f60f195d57904e",
                        "creationDate": "2019-05-02T00:00:00.000+0000",
                        "year": 2019,
                        "month": 5,
                        "name": "VIR PERIODIQUE - ASSURANCE VIE NATHAN",
                        "amount": -50.0,
                        "category": "Epargne",
                        "subcategory": "Assurance Vie Nathan",
                        "recipient": "Boursorama",
                        "comment": null
                    }
                ];
            }

        }
    };
</script>

