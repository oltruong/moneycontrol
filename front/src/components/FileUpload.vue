<template>
  <div class="col-12">
    <form>
      <div class="form-group">
        <label for="file">File</label>
        <input type="file" class="form-control-file" id="file" ref="file"
               @change="handleFileUpload()">
        <button @click="submitFile()">Submit</button>
      </div>
    </form>
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
      this.$router.push( {name: "unclassifiedOperations"});
    },

    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
};
</script>

<style scoped lang="scss">

</style>
