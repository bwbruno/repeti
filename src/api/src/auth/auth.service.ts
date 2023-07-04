import { Injectable, UnauthorizedException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UsuarioDao } from '../dal/usuario/usuario-dao';
import { UsuarioBuilder } from '../core/models/impl/usuario-builder';

@Injectable()
export class AuthService {
  constructor(private usuarioDao: UsuarioDao, private jwtService: JwtService) {}

  async login(username, pass) {
    const usuario = await this.usuarioDao.recuperarPorEmail({ data: username });

    if (!(await usuario.compararSenha(pass))) {
      throw new UnauthorizedException();
    }
    const payload = { username: usuario.email, sub: usuario.id };
    return {
      access_token: await this.jwtService.signAsync(payload),
    };
  }
}
