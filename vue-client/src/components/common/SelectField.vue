<template>
  <div class="select-field">
    <label :for="name">
      {{ label }}
    </label>

    <br/>

    <select
        :id="name"
        :name="name"
        :aria-errormessage="errorId"
        :aria-invalid="error !== null"
        v-model="inputValue"
        :class="!error || 'error'"
    >
      <option
          v-for="o in options"
          :key="o"
      >
        {{ o }}
      </option>
    </select>

    <div v-show="error" :id="errorId" class="error-text">
      {{ error }}
    </div>
  </div>
</template>

<script>
export default {
  name: "SelectField",
  props: {
    value: [String, Number],
    label: {
      required: true,
      type: String,
    },
    name: {
      required: true,
      type: String,
    },
    error: {
      type: String,
      default: null,
    },
    options: {
      type: Array,
      required: true,
    }
  },
  computed: {
    inputValue: {
      get() {
        return this.value;
      },
      set(input) {
        this.$emit('input', input);
      }
    },
    errorId() {
      return this.name + '-error';
    }
  }
}
</script>

<style scoped>
.select-field {
  margin: 0 0 32px 0;
}

label {
  font-size: 24px;
  font-weight: bold;
}

select {
  border: 2px solid #000000;
  font-size: 22px;
  margin-top: 8px;
  outline: none;
  padding: 6px 12px;
  border-radius: 4px;
}

select.error {
  border: 2px solid red;
}

.error-text {
  margin-top: 12px;
  color: red;
  font-size: 22px;
}

@media only screen and (max-width: 700px) {
  select {
    width: 100%;
  }
}
</style>
