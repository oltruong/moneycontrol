<template>
  <form>
    <div class="form-group" @submit.prevent="onSubmit">
      <label for="nameCondition">Nom</label>
      <input class="form-control" id="nameCondition" aria-describedby="nameHelp"
             placeholder="" v-model="nameCondition">
      <label for="amountCondition">Montant</label>
      <input class="form-control" id="amountCondition" aria-describedby="amountHelp"
             placeholder="" v-model="amountCondition">
      <label for="category">Catégorie</label>
      <input class="form-control" id="category" placeholder="Catégorie" v-model="category"
             @keyup.enter="onSubmit">
      <label for="subcategory">Sous catégorie</label>
      <input class="form-control" id="subcategory" placeholder="Sous Catégorie"
             v-model="subcategory" @keyup.enter="onSubmit">
      <label for="recipient">Destinataire</label>
      <input class="form-control" id="recipient" placeholder="Destinataire"
             v-model="recipient" @keyup.enter="onSubmit">
      <label for="recipient">Comment</label>
      <input class="form-control" id="comment" placeholder="Commentaire" v-model="comment"
             @keyup.enter="onSubmit">
      <br>
      <button @click="onSubmit">Ajouter</button>

    </div>
  </form>
</template>

<script>
export default {
  name: "NewRule",
  data: function () {
    return {
      nameCondition: "",
      amountCondition: "",
      category: "",
      subcategory: "",
      recipient: "",
      comment: ""
    }
  },
  methods: {
    onSubmit() {

      if (this.nameCondition && this.category && this.subcategory) {
        let new_rule = {
          nameCondition: this.nameCondition,
          category: this.category,
          subcategory: this.subcategory,
          recipient: this.recipient,
          comment: this.comment
        };

        if (this.amountCondition) {
          new_rule.amountCondition = this.amountCondition;
        }
        this.$emit('add-rule', new_rule);
        this.nameCondition = "";
        this.amountCondition = "";
        this.category = "";
        this.subcategory = "";
        this.recipient = "";
        this.comment = "";
      }
    },

  }
};
</script>

<style scoped lang="sass">

</style>
