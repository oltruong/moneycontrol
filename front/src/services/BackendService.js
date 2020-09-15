import axios from 'axios'

const apiClient = axios.create({
                                   baseURL: process.env.VUE_APP_BACKOFFICE_URL,
                                   withCredentials: false,
                                   headers: {
                                       Accept: 'application/json',
                                       'Content-Type': 'application/json'
                                   }
                               })

export default {
    getLiveStatus() {
        return apiClient.get('/health/live')
    },
    getOperations(year_parameter, month_parameter) {
        return apiClient.get('rest/operations'
                             + year_parameter
                             + month_parameter)
    },
    getUnclassifiedOperations() {
        return apiClient.get('rest/operations?category=null')
    },
    refreshOperations() {
        return apiClient.post("/rest/operations/refresh", {})
    },
    updateOperation(operation){
      return    apiClient.put( "rest/operations/" + operation.id,operation)
    },
    getRules() {
        return apiClient.get('/rest/rules')
    },
    addRule(rule) {
        return apiClient.post("/rest/rules", rule)
    },
    updateRule(rule) {
        return apiClient.put("/rest/rules/" + rule.id,
                             rule)
    },
    getStatistics(year_parameter) {
        return apiClient.get("rest/statistics" + year_parameter)
    },
    uploadOperations(formData){
        return  apiClient.post( "/rest/bankfileupload",
                           formData,
                           {
                               headers: {
                                   'Content-Type': 'multipart/form-data'
                               }
                           }
        )
    }
}
