
export class BaseConstant {
    // static baseUrl:string = "http://localhost:8080";
    static baseUrl:string = import.meta.env.VITE_API_URL;
}