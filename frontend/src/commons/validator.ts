interface Data {
  key: string;
  msg: string;
  func: () => boolean;
}

const validator = (datas: Data[], errors: Map<string, string>) => {
  const dataMap: Map<string, string> = new Map();

  datas.forEach((d) => {
    dataMap.set(d.key, d.msg);
  });

  const validateAll = () => {
    let result = true;

    datas.forEach((d) => {
      const r = validate(d.key);

      result &&= r;
    });

    return result;
  };

  const validate = (key: string) => {
    const data = datas.filter((d) => d.key === key);

    if (data.length > 0 && data[0].func()) {
      errors.delete(key);
      return true;
    }

    errors.set(key, dataMap.get(key)!);
    return false;
  };

  return { validateAll, validate };
};

export default validator;
