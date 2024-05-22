interface Data {
  msg: string;
  func: () => boolean;
}

const validator = (datas: Map<string, Data>, errors: Map<string, string>) => {
  const validateAll = () => {
    let result = true;

    datas.forEach((_, k) => {
      if (!validate(k) && result) result = false;
    });

    return result;
  };

  const validate = (key: string) => {
    const data = datas.get(key);

    if (!data) {
      throw new Error("Not Exist Key.");
    }

    if (data.func()) {
      errors.delete(key);
      return true;
    }

    errors.set(key, data.msg);
    return false;
  };

  return { validateAll, validate };
};

export default validator;
export { type Data };
