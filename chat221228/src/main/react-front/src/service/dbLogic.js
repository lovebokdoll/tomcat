import axios from "axios";

export const jsonDeptList = (params) => {
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "get",
        url: process.env.REACT_APP_CHAT221228_IP + "dept/jsonDeptList1",
        params: params,
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};

/* rafce단축키 -> arrowfunction */
