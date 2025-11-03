import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private base = 'http://localhost:8081/api/auth';
  constructor(private http: HttpClient) {}

  register(body: any) { return this.http.post<any>(`${this.base}/register`, body); }
  login(body: any) { return this.http.post<any>(`${this.base}/login`, body); }

  setToken(token: string) { localStorage.setItem('token', token); }
  getToken(): string | null { return localStorage.getItem('token'); }
  logout() { localStorage.removeItem('token'); }

  isSeller(): boolean {
    const token = this.getToken();
    if (!token) return false;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload?.role === 'SELLER';
    } catch { return false; }
  }

  getUserId(): string | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload?.sub || payload?.userId || null;
    } catch { return null; }
  }
}
