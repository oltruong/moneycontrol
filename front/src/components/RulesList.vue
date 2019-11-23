<template>
    <div class="col-12">

        <div class="row">
            <div class="col-2">
                <h2>Filtre</h2>
                <input type="text" v-model="filter">
                <h5>{{filtered_rules.length}} règle<span
                        v-if="filtered_rules.length > 1">s</span>
                </h5>
            </div>

            <div class="col-10">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope=" col" @click="set_sort_by('nameCondition')">Nom
                            <i class="fas fa-caret-up" v-show="sort_by=='nameCondition' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='nameCondition' && !asc"></i>
                        </th>
                        <th scope="col" @click="set_sort_by('amountCondition')">Montant
                            <i class="fas fa-caret-up"
                               v-show="sort_by=='amountCondition' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='amountCondition' && !asc"></i>

                        </th>
                        <th scope="col" @click="set_sort_by('category')">Cat&eacute;gorie
                            <i class="fas fa-caret-up"
                               v-show="sort_by=='category' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='category' && !asc"></i>

                        </th>
                        <th scope="col" @click="set_sort_by('subcategory')">Ss. Cat.
                            <i class="fas fa-caret-up"
                               v-show="sort_by=='subcategory' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='subcategory' && !asc"></i>
                        </th>
                        <th scope="col" @click="set_sort_by('recipient')">Destinataire
                            <i class="fas fa-caret-up"
                               v-show="sort_by=='recipient' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='recipient' && !asc"></i>
                        </th>
                        <th scope="col" @click="set_sort_by('comment')">Commentaire
                            <i class="fas fa-caret-up"
                               v-show="sort_by=='comment' && asc"></i>
                            <i class="fas fa-caret-down"
                               v-show="sort_by=='comment' && !asc"></i>

                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="rule of filtered_rules">
                        <td>
                            <editable-field :parent-object="rule" :attribute="'nameCondition'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                        <td>
                            <editable-field :parent-object="rule" :attribute="'amountCondition'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                        <td>
                            <editable-field :parent-object="rule" :attribute="'category'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                        <td>
                            <editable-field :parent-object="rule" :attribute="'subcategory'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                        <td>
                            <editable-field :parent-object="rule" :attribute="'recipient'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                        <td>
                            <editable-field :parent-object="rule" :attribute="'comment'"
                                            @new-value="saveRule"></editable-field>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<style lang="sass">
    tfoot, thead
        font-weight: bold
</style>
<script>
    import axios from 'axios';
    import EditableField from "../components/EditableField";

    export default {
        name: "RulesList",
        props: {
            rules: Array

        },
        components: {EditableField},
        data: function () {
            return {
                filter: "",
                sort_by: "name",
                asc: true,
            }
        },
        computed: {
            filtered_rules() {
                return this.filtered_rules_by_name().sort((a, b) => {
                    let result = 0;
                    if (this.sort_by === "nameCondition") {
                        result =
                            this.wrapper(a.nameCondition)
                                .localeCompare(this.wrapper(b.nameCondition));
                    } else if (this.sort_by === "category") {
                        result = this.wrapper(a.category).localeCompare(this.wrapper(b.category));
                    } else if (this.sort_by === "subcategory") {
                        result =
                            this.wrapper(a.subcategory).localeCompare(this.wrapper(b.subcategory));
                    } else if (this.sort_by === "comment") {
                        result = this.wrapper(a.comment).localeCompare(this.wrapper(b.comment));
                    } else if (this.sort_by === "recipient") {
                        result = this.wrapper(a.recipient).localeCompare(this.wrapper(b.recipient));
                    } else if (this.sort_by === "amountCondition") {
                        result = a.amountCondition - b.amountCondition;
                    }

                    return (this.asc ? result : -result);

                });
            },

        },
        methods: {
            filtered_rules_by_name() {
                return this.rules.filter(rule => this.rule_matches(rule));
            },
            rule_matches(rule) {
                return this.contains(rule, 'nameCondition')
                       || this.contains(rule, 'category')
                       || this.contains(rule, 'subcategory')
                       || this.contains(rule, 'recipient')
                       || this.contains(rule, 'amountCondition')
                       || this.contains(rule, 'comment');
            },
            contains(rule, item) {
                return this.wrapper(rule[item]).toLowerCase()
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
            saveRule(rule, field, newValue) {
                rule[field] = newValue;
                axios.put(
                    process.env.VUE_APP_BACKOFFICE_URL + "rest/rules/" + rule.id,
                    rule)
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    };
</script>

