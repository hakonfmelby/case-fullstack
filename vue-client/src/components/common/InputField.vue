<template>
  <div class="input-field">
    <label :for="name">
      {{ label }}
    </label>

    <br/>

    <input
        :maxlength="maxLength"
        :id="name"
        :name="name"
        :type="type"
        :placeholder="placeholder"
        :aria-errormessage="errorId"
        :aria-invalid="error !== null"
        v-model="inputValue"
        :class="!error || 'error'"
    />

    <div v-show="error" :id="errorId" class="error-text">
      {{ error }}
    </div>
  </div>
</template>

<script>
export default {
  name: "InputField",
  props: {
    value: [String, Number],
    maxLength: {
      type: Number,
      default: 255,
    },
    label: {
      required: true,
      type: String,
    },
    name: {
      required: true,
      type: String,
    },
    type: {
      default: 'text',
      type: String,
    },
    error: {
      type: String,
      default: null,
    },
    placeholder: {
      type: String,
      default: null,
    },
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
.input-field {
  margin: 0 24px 32px 0;
}

label {
  font-size: 24px;
  font-weight: bold;
}

input {
  border: 2px solid #000000;
  font-size: 22px;
  margin-top: 8px;
  padding: 6px 12px;
  border-radius: 4px;
}

input.error {
  border: 2px solid red;
}

.error-text {
  margin-top: 12px;
  color: red;
  font-size: 22px;
}

@media only screen and (max-width: 700px) {
  input {
    width: 100%;
  }
}
</style>
