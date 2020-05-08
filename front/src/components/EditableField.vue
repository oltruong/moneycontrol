<template>
    <span>
        <span @click="edit" v-show="!editMode">{{wrapValue}}</span>
        <input type="text" v-show="editMode" v-model="value" @blur="save">
    </span>

</template>

<script>
    export default {
        name: "EditableField",
        props: {
            parentObject: {
                type: Object,
                required: true
            },
            attribute: {
                type: String,
                required: true
            }
        },
        watch: {
            parentObject: function (newVal, oldVal) {
                this.value = newVal[this.attribute];
            }
        },
        data: function () {
            return {
                value: this.parentObject[this.attribute],
                editMode: false
            }
        },
        computed: {
            wrapValue() {
                if (this.value === null || this.value === "" || this.value === undefined) {
                    return "N/A"
                } else {
                    return this.value
                }
            }
        },
        methods: {
            edit() {
                this.editMode = true
            },
            save() {
                if (this.parentObject[this.attribute] !== this.value) {
                    this.$emit('new-value', this.parentObject, this.attribute, this.value);
                }
                this.editMode = false
            }
        }
        //
        //         if (this.nameCondition && this.category && this.subcategory) {
        //             let new_rule = {
        //                 nameCondition: this.nameCondition,
        //                 category: this.category,
        //                 subcategory: this.subcategory,
        //                 recipient: this.recipient,
        //                 comment: this.comment
        //             };
        //
        //             if (this.amountCondition) {
        //                 new_rule.amountCondition = this.amountCondition;
        //             }
        //             this.$emit('add-rule', new_rule);
        //             this.nameCondition = "";
        //             this.amountCondition = "";
        //             this.category = "";
        //             this.subcategory = "";
        //             this.recipient = "";
        //             this.comment = "";
        //         }
        //     },
        //
        // }
    };
</script>

<style scoped lang="sass">

</style>
