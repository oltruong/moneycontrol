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

    import axios from 'axios';

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

                const url = process.env.VUE_APP_BACKOFFICE_URL + "/rest/bankfileupload";
                axios.post(url,
                           formData,
                           {
                               headers: {
                                   'Content-Type': 'multipart/form-data'
                               }
                           }
                ).then(function () {
                    console.log('SUCCESS!!');
                })
                    .catch(function () {
                        console.log('FAILURE!!');
                    });
            },

            /*
              Handles a change on the file upload
            */
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
