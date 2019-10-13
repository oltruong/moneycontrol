<template>
    <span>
        <span v-show="!editMode">{{value}}</span>
        <input type="text" v-show="editMode" v-model="value" @blur="save">
    </span>

</template>

<script>
    export default {
        name: "EditableField",
        props: {
            parentObject: Object,
            attribute: String,
            editMode: Boolean,
        },
        data: function () {
            return {
                value: this.parentObject[this.attribute]
            }
        },
        methods: {
            save() {
                this.$emit('new-value', this.parentObject, this.attribute, this.value);
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
