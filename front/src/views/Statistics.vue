<template>
  <div class="row">
    <div class="col-12">
      <h1>Statistiques
      </h1>
      <h2>
        <router-link :to="previous">
          <em class="fas fa-step-backward"></em>
        </router-link>
        {{ current }}
        <router-link :to="next">
          <em class="fas fa-step-forward"></em>
        </router-link>
      </h2>
    </div>


    <div class="col-12">

      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link active" id="operation-tab" data-toggle="tab" href="#expense"
             role="tab"
             aria-controls="home" aria-selected="true">Dépenses</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="income-tab" data-toggle="tab" href="#income" role="tab"
             aria-controls="contact" aria-selected="false">Recettes</a>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="expense" role="tabpanel"
             aria-labelledby="operation-tab">
          <div class="row">
            <div class="col-12">
              <highcharts class="chart" :options="expenseChart"
                          :updateArgs="updateArgs"></highcharts>
            </div>

            <div class="col-6">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th scope="col">Catégorie</th>
                  <th scope="col">Total</th>
                  <th scope="col">%</th>
                  <th scope="col">Moyenne</th>
                  <th scope="col">Écart type</th>

                </tr>
                </thead>
                <tbody>
                <tr v-for="expense of expenseStatistics">
                  <td>{{ expense.name }}</td>
                  <td>{{ expense.sum }}</td>
                  <td>{{ expense.percentage }}</td>
                  <td>{{ expense.average }}</td>
                  <td>{{ expense.standardDeviation }}</td>

                </tr>
                </tbody>
              </table>
            </div>
            <div class="col-6">
              <highcharts class="chart" :options="expensePie"
                          :updateArgs="updateArgs"></highcharts>
            </div>
          </div>
        </div>
        <div class="tab-pane fade show" id="income" role="tabpanel"
             aria-labelledby="operation-tab">
          <div class="row">
            <div class="col-12">
              <highcharts class="chart" :options="incomeChart"
                          :updateArgs="updateArgs"></highcharts>
            </div>

            <div class="col-6">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th scope="col">Catégorie</th>
                  <th scope="col">Total</th>
                  <th scope="col">%</th>
                  <th scope="col">Moyenne</th>
                  <th scope="col">Écart type</th>

                </tr>
                </thead>
                <tbody>
                <tr v-for="expense of incomeStatistics">
                  <td>{{ expense.name }}</td>
                  <td>{{ expense.sum }}</td>
                  <td>{{ expense.percentage }}</td>
                  <td>{{ expense.average }}</td>
                  <td>{{ expense.standardDeviation }}</td>

                </tr>
                </tbody>
              </table>
            </div>
            <div class="col-6">
              <highcharts class="chart" :options="incomePie"
                          :updateArgs="updateArgs"></highcharts>
            </div>
          </div>

        </div>
      </div>
    </div>


  </div>
</template>

<script>
import _ from 'lodash';
import moment from "moment";
import BackendService from "@/services/BackendService";

function positiveAmount() {
  return x => x.amount > 0;
}

function negativeAmount() {
  return x => x.amount <= 0;
}

export default {
  name: "statistics",
  data: function () {
    return {
      current: "",
      statistics: [],
      expenseStatistics: [],
      incomeStatistics: [],
      updateArgs: [true, true, {duration: 1000}],
      incomeChart: {
        chart: {
          type: 'column'
        },
        title: {
          text: 'Produits'
        },
        series: []
      },
      incomePie: {
        chart: {
          type: 'pie'
        },
        title: {
          text: 'Produits'
        },
        series: []
      },
      expenseChart: {
        chart: {
          type: 'column'
        },
        title: {
          text: 'Dépenses'
        },
        series: []
      },
      expensePie: {
        chart: {
          type: 'pie'
        },
        title: {
          text: 'Dépenses'
        },
        series: []
      },
    }
  },
  watch: {
    $route(to, from) {
      this.load_statistics()
    }
  },
  mounted() {
    this.load_statistics()
  },
  methods: {
    load_statistics() {
      const year_parameter = this.$route.query.year
                             == undefined ? "" : "?year=" + this.$route.query.year;
      this.current = moment(this.$route.query.year,
                            "YYYY", 'fr').format('YYYY')
      BackendService.getStatistics(year_parameter)
          .then(response => {
            this.statistics = response.data;
            this.incomeChart.series = this.compute_series(positiveAmount(), 1);
            this.incomePie.series = [{
              data: this.compute_pie(positiveAmount(), 1)
            }];
            this.incomeStatistics = this.compute_table(positiveAmount(), 1)

            this.expenseChart.series = this.compute_series(negativeAmount(), -1);
            this.expensePie.series = [{
              data: this.compute_pie(negativeAmount(), -1)
            }];
            this.expenseStatistics = this.compute_table(negativeAmount(), -1)
          });

      this.previous = "/statistics?year=" + (Number(this.$route.query.year) - 1);
      this.next = "/statistics?year=" + (Number(this.$route.query.year) + 1);
    },

    compute_series(filterAmount, factor) {
      return _(this.statistics)
          .sortBy(['category', 'year', 'month'])
          .filter(filterAmount)
          .groupBy('category')
          .map((values, category) => {
            return {
              name: category,
              // color: '#6fcd98',
              data: _(values).map(data => {
                return [data.month, factor * data.amount]
              }).value(),
            }
          })
          .value();
    },

    compute_pie(filterAmount, factor) {
      return _(this.statistics)
          .filter(filterAmount)
          .groupBy('category')
          .map((values, category) => {
            return {
              name: category,
              y: factor * _.sumBy(values, 'amount'),
            }
          })
          .value();
    },

    compute_table(filterAmount, factor) {

      const total = factor * _(this.statistics)
          .filter(filterAmount)
          .map('amount')
          .sum();

      return _(this.statistics)
          .filter(filterAmount)
          .groupBy('category')
          .map((values, category) => {
            let sum = factor * _.sumBy(values, 'amount');
            let average = factor * _.meanBy(values, 'amount');
            let population = values.length;
            return {
              name: category,
              sum: sum.toFixed(2),
              percentage: (sum * 100 / total).toFixed(2),
              average: average.toFixed(2),
              standardDeviation: Math.sqrt(_(values)
                                               .map(i => (Math.pow(
                                                   factor * i.amount - average, 2)
                                                          / population))
                                               .sum()).toFixed(2),
            }
          })
          .value();
    },
  }
};


</script>
