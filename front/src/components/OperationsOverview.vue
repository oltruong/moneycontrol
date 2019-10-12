<template>
    <div class="col-12">

        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="operation-tab" data-toggle="tab" href="#operation"
                   role="tab"
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
                <operations-list :operations="operations"></operations-list>

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
                    <div class="col-12"><h2>Recettes</h2>
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
        </div>

    </div>

</template>
<style lang="sass">
    tfoot, thead
        font-weight: bold

        .tab-content
            margin-top: 20px

</style>
<script>
    import OperationsList from "../components/OperationsList";

    export default {
        name: "OperationsOverview",
        props: {
            operations: Array
        },
        components: {OperationsList},
        data: function () {
            return {
                filter: "",
                sort_by: "creationDate",
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
                        result =
                            this.wrapper(a.subcategory).localeCompare(this.wrapper(b.subcategory));
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
                return this.sum_amount(this.filtered_operations_by_name()).toFixed(2);
            },
            positive_categories() {
                return this.group_by_categories(i => i > 0)
                    .sort((a, b) => this.wrapper(a.name).localeCompare(this.wrapper(b.name)));
            },
            positive_categories_total() {
                return this.sum_amount(this.group_by_categories(i => i > 0)).toFixed(2);
            },
            negative_categories() {
                return this.group_by_categories(i => i <= 0)
                    .sort((a, b) => this.wrapper(a.name).localeCompare(this.wrapper(b.name)));
            },
            negative_categories_total() {
                return this.sum_amount(this.group_by_categories(i => i <= 0)).toFixed(2);
            },
            positive_subcategories() {
                return this.group_by_subcategories(i => i > 0).sort(
                    (a, b) => this.wrapper(a.category).localeCompare(this.wrapper(b.category)));
            },
            negative_subcategories() {
                return this.group_by_subcategories(i => i <= 0).sort(
                    (a, b) => this.wrapper(a.category).localeCompare(this.wrapper(b.category)));
            }

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
                return this.wrapper(operation[item]).toLowerCase()
                           .indexOf(this.filter.toLowerCase()) > -1
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
                    const total = this.sum_amount(categories[category]);
                    if (filter(total)) {
                        results.push({
                                         name: category,
                                         amount: total.toFixed(2)
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
                                             amount: total.toFixed(2)
                                         });
                        }
                    }

                }
                return results;
            },
            sum_amount(operation_list) {
                return operation_list.map(i => Number(i.amount)).reduce((a, b) => a + b, 0);
            },

        }
    };
</script>

