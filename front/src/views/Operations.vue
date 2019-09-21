<template>
    <div class="row">
        <div class="col-12">
            <h1>Opérations
            </h1>
            <h2>
                <router-link to="/operations/2019/8">
                    <em class="fas fa-step-backward"></em>
                </router-link>
                <router-link to="/operations/2019/10">
                    <em class="fas fa-step-forward"></em>
                </router-link>
            </h2>
        </div>

        <operations-list :operations="operations"></operations-list>
    </div>
</template>

<script>
    import moment from 'moment';
    import OperationsList from "../components/OperationsList";

    export default {
        name: "operations",
        components: {OperationsList},
        data: function () {
            return {
                operations: this.get_operations(),
            }
        },
        watch: {
            $route(to, from) {
                console.log("WATCH ROUTE ");
                console.log(to);
                console.log(from);
                console.log("-------");
                this.operations = this.get_operations();
            }
        },
        methods: {
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
