<template>
  <div class="row">

    <div class="col-12">
      <form>
        <div class="form-group">
          <!--          <label for="file">File</label>-->
          <br><br>
          <input type="file" class="form-control-file" id="file" ref="file"
                 @change="handleFileUpload()">
          <br><br>
          <button @click="submitFile()" class="submitfile">Charger les opérations</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import BackendService from "@/services/BackendService";

export default {
  name: "FileUpload",
  data() {
    return {
      file: ''
    }
  },
  methods: {
    submitFile() {
      let formData = new FormData();
      formData.append('operations', this.file);

      BackendService.uploadOperations(formData)
          .then(function () {
            console.info('SUCCESS!!');
          })
          .catch(function () {
            console.error('FAILURE!!');
          });

      console.info("FINI");
      this.$router.push({name: "unclassifiedOperations"});
    },

    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
};
</script>

<style scoped lang="sass">
.submitfile
  color: white
  padding: 10px
  background-color: green

</style>
