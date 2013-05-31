/*
 * Copyright (C) 2013 Google, Inc.
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test;

import com.google.autofactory.AutoFactory;
import com.google.autofactory.Param;

import javax.inject.Inject;
import javax.inject.Singleton;

class Factories {
  
  static class A {
    public @Inject A() {}
  }
  
  /** Class with no injectable and one field as factory paramter */
  static class B {
    @Param String s;
    @AutoFactory interface AFactory {
      B makeB(String s);
    }
  }

  /** Class with one field injectable and one field as factory paramter */
  static class C {
    @Inject A a;
    @Param String s;
    @AutoFactory interface AFactory {
      C makeC(String s);
    }
  }
  
  /** Class with one field injectable and one constructor paramter */
  static class D {
    @Inject A a;
    final String s;
    D(@Param String s) { this.s = s; }
    @AutoFactory interface AFactory {
      D create(String s);
    }
  }

  /** Class with one constructor injectable and one constructor paramter */
  static class E {
    final A a;
    final String s;
    @Inject E(A a, @Param String s) { this.a = a; this.s = s; }
    @AutoFactory interface AFactory {
      E create(String s);
    }
  }
}