import {
  Body,
  Controller,
  Get,
  HttpCode,
  HttpStatus,
  Post,
  Request,
} from '@nestjs/common';
import { AuthService } from './auth.service';
import { Public } from '../core/decorators/public.decorator';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Public()
  @HttpCode(HttpStatus.OK)
  @Post('login')
  login(@Body() params: Record<string, any>) {
    return this.authService.login(params.email, params.senha);
  }

  @Get('profile')
  getProfile(@Request() req) {
    return req.user;
  }
}
